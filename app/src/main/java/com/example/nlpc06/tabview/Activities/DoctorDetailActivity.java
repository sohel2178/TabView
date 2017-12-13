package com.example.nlpc06.tabview.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nlpc06.tabview.Adater.PatientAdapter;
import com.example.nlpc06.tabview.Model.Doctor;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;
import com.example.nlpc06.tabview.Utility.MyUtils;

import io.realm.Realm;
import io.realm.RealmResults;

public class DoctorDetailActivity extends AppCompatActivity {

    private Doctor doctor;

    private PatientAdapter adapter;

    // View]
    private ImageView ivImage;
    private TextView tvName,tvDegree,tvSpeciality,tvContact;
    private RecyclerView rvPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        adapter = new PatientAdapter(getApplicationContext(),true);

        final int id = getIntent().getIntExtra("id",-1);

        initView();

        if(id != -1){
            Realm realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    doctor = realm.where(Doctor.class).equalTo("id",id).findFirst();

                    tvName.setText(doctor.getName());
                    tvDegree.setText(doctor.getDegree());
                    tvSpeciality.setText(doctor.getSpeciality());
                    tvContact.setText(doctor.getContact());

                    Bitmap bitmap = MyUtils.getBitmap(doctor.getImagePath());
                    ivImage.setImageBitmap(bitmap);

                    RealmResults<Patient> patientList = realm.where(Patient.class).equalTo("doctor_id",id).findAll();

                    for(Patient x: patientList){
                        adapter.addPatient(x);
                    }
                }
            });
        }



    }

    private void initView() {
        ivImage = (ImageView) findViewById(R.id.image);

        tvName = (TextView) findViewById(R.id.name);
        tvDegree = (TextView) findViewById(R.id.degree);
        tvSpeciality = (TextView) findViewById(R.id.speciality);
        tvContact = (TextView) findViewById(R.id.contact);

        rvPatient = (RecyclerView) findViewById(R.id.rv_patient);
        rvPatient.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvPatient.setAdapter(adapter);
    }
}
