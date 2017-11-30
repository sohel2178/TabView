package com.example.nlpc06.tabview.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nlpc06.tabview.Adater.CabinSpinnerAdapter;
import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddPatientActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName,etAge,etAddress,etContact;
    private Spinner spCabin;
    private Button btnAdd,btnSelect;
    private ImageView ivImage;

    CabinSpinnerAdapter adapter;

    String path ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        adapter = new CabinSpinnerAdapter(getApplicationContext());

        initView();

    }

    private void initView() {

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Cabin> cabinList = realm.where(Cabin.class).equalTo("status",false).findAll();

                for (Cabin x:cabinList){
                    adapter.addCabin(x);
                }
            }
        });


        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        etAddress = (EditText) findViewById(R.id.et_address);
        etContact = (EditText) findViewById(R.id.et_contact);

        ivImage = (ImageView) findViewById(R.id.image);

        spCabin = (Spinner) findViewById(R.id.sp_select_cabin);
        spCabin.setAdapter(adapter);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnSelect = (Button) findViewById(R.id.btn_select);

        btnAdd.setOnClickListener(this);
        btnSelect.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_select:
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
                break;

            case R.id.btn_add:
                String name = etName.getText().toString();
                String ageStr = etAge.getText().toString();
                String address = etAddress.getText().toString();
                String contact = etContact.getText().toString();

                int age =0;

                if(!TextUtils.isEmpty(ageStr)){
                    age = Integer.parseInt(ageStr);
                }


                int pos = spCabin.getSelectedItemPosition();

                final int cabinId =(int) adapter.getItemId(pos);

                final Patient patient = new Patient(name,age,address,cabinId,contact);

                if(path!=null && !path.equals("") ){
                    Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
                    patient.setImagePath(path);
                }else {
                    Toast.makeText(this, "Path Not Found", Toast.LENGTH_SHORT).show();
                    return;
                }


                Realm realm = Realm.getDefaultInstance();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Number currentIdNum = realm.where(Patient.class).max("id");

                        int nextId;

                        if(currentIdNum==null){
                            nextId =1;
                        }else{
                            nextId = currentIdNum.intValue()+1;
                        }
                        patient.setId(nextId);

                        // Insert Patient into Database
                        realm.insert(patient);

                        //Get the Cabin from database by its Id
                        Cabin cabin = realm.where(Cabin.class).equalTo("id",cabinId).findFirst();
                        // change the Status
                        cabin.setStatus(true);
                        // Update Cabin into database
                        realm.insertOrUpdate(cabin);

                        AddPatientActivity.this.finish();
                    }
                });
                break;
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                path = resultUri.getPath();
                ivImage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
