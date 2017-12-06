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

import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.Model.Doctor;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import io.realm.Realm;

public class AddDoctorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName,etDegree,etSpeciality,etContact;
    private Button btnAdd,btnSelect;
    private ImageView ivImage;

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        initView();
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.et_name);
        etDegree = (EditText) findViewById(R.id.et_degree);
        etSpeciality = (EditText) findViewById(R.id.et_speciality);
        etContact = (EditText) findViewById(R.id.et_contact);

        ivImage = (ImageView) findViewById(R.id.image);


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
                String degre = etDegree.getText().toString();
                String speciality = etSpeciality.getText().toString();
                String contact = etContact.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this, "Name Field Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(degre)){
                    Toast.makeText(this, "Degree Field Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(speciality)){
                    Toast.makeText(this, "speciality Field Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(contact)){
                    Toast.makeText(this, "contact Field Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(path)){
                    Toast.makeText(this, "Path  Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Doctor doctor = new Doctor(name,degre,speciality,contact,path);

                Realm realm = Realm.getDefaultInstance();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Number currentIdNum = realm.where(Doctor.class).max("id");

                        int nextId;

                        if(currentIdNum==null){
                            nextId =1;
                        }else{
                            nextId = currentIdNum.intValue()+1;
                        }

                        doctor.setId(nextId);

                        realm.insert(doctor);

                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("doctor",doctor);
                        intent.putExtras(bundle);

                        setResult(RESULT_OK,intent);

                        AddDoctorActivity.this.finish();
                    }
                });

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                path = resultUri.getPath();

                Log.d("HHH",path);
                ivImage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
