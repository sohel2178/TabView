package com.example.nlpc06.tabview.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.Model.Doctor;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.Model.Release;
import com.example.nlpc06.tabview.R;
import com.example.nlpc06.tabview.Utility.MyUtils;

import io.realm.Realm;

public class PatientDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private int patient_id;

    private Patient patient;

    // Declare View
    private TextView tvName,tvAddress,tvAge,tvContact,tvCabin;

    private ImageView ivImage;

    private Button btnRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        initView();

        patient_id = getIntent().getIntExtra("id",-1);

        if(patient_id!=-1){
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    patient =realm.where(Patient.class).equalTo("id",patient_id).findFirst();
                    if(patient!=null){

                        tvName.setText(patient.getName());
                        tvAddress.setText(patient.getAddress());
                        tvAge.setText(String.valueOf(patient.getAge()));
                        tvContact.setText(patient.getContact_number());

                        Bitmap bitmap = MyUtils.getBitmap(patient.getImagePath());

                        ivImage.setImageBitmap(bitmap);

                        Cabin cabin = realm.where(Cabin.class).equalTo("id",patient.getCabin_id()).findFirst();

                        tvCabin.setText(cabin.getCabin_num());
                    }
                }
            });
        }

    }

    private void initView() {
        tvName = (TextView) findViewById(R.id.name);
        tvAddress = (TextView) findViewById(R.id.address);
        tvAge = (TextView) findViewById(R.id.age);
        tvContact = (TextView) findViewById(R.id.contact);
        tvCabin = (TextView) findViewById(R.id.cabin);

        ivImage = (ImageView) findViewById(R.id.image);

        btnRelease = (Button) findViewById(R.id.btn_release);
        btnRelease.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(patient!=null){
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {




                    // Update Cabin

                    Cabin cabin = realm.where(Cabin.class).equalTo("id",patient.getCabin_id()).findFirst();
                    cabin.changeStatus();
                    realm.insertOrUpdate(cabin);

                    // Add Patient in Release Table

                    Release release = new Release(patient);

                    Number currentIdNum = realm.where(Release.class).max("id");

                    int nextId;

                    if(currentIdNum==null){
                        nextId =1;
                    }else{
                        nextId = currentIdNum.intValue()+1;
                    }

                    release.setId(nextId);

                    realm.insert(release);



                    Intent intent = new Intent();
                    intent.putExtra("patId",patient_id);

                    setResult(RESULT_OK,intent);

                    Patient pat = realm.where(Patient.class).equalTo("id",patient.getId()).findFirst();
                    // Remove Patient
                    pat.deleteFromRealm();

                    PatientDetailActivity.this.finish();

                }
            });



        }

    }
}
