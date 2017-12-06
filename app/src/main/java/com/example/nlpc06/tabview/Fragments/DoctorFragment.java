package com.example.nlpc06.tabview.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nlpc06.tabview.Activities.AddDoctorActivity;
import com.example.nlpc06.tabview.Adater.DoctorAdapter;
import com.example.nlpc06.tabview.Listener.DoctorClickListener;
import com.example.nlpc06.tabview.Model.Doctor;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileOutputStream;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorFragment extends Fragment implements OnClickListener,DoctorClickListener {

    private static final int REQ_CODE=1452;
    private FloatingActionButton fabAdd;
    private RecyclerView rvDoctor;

    private DoctorAdapter adapter;




    public DoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new DoctorAdapter(getContext());
        adapter.setDoctorClickListener(this);

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Doctor> doctorList = realm.where(Doctor.class).findAll();

                for(Doctor x: doctorList){
                    adapter.addDoctor(x);
                }
            }
        });


        //Log.d("GGG",root);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        rvDoctor = view.findViewById(R.id.rv_doctor);

        rvDoctor.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDoctor.setAdapter(adapter);

        fabAdd = view.findViewById(R.id.button);
        fabAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getContext(), AddDoctorActivity.class);
        startActivityForResult(intent,REQ_CODE);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==REQ_CODE && resultCode==getActivity().RESULT_OK){

            Doctor doctor = (Doctor) data.getSerializableExtra("doctor");

            if(doctor!=null){
                adapter.addDoctor(doctor);
            }
        }
    }

    private void saveBitmap(Bitmap bitmap){
        String root = Environment.getExternalStorageDirectory().toString();

        String appName =getString(R.string.app_name);

        File myDir = new File(root + "/"+appName);

        if(!myDir.exists()){
            myDir.mkdirs();
        }

        long time = System.currentTimeMillis();

        File file = new File(myDir,time+".jpg");

        if(file.exists()){
            file.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDoctorClick(final Doctor doctor) {
        //Toast.makeText(getContext(), doctor.getName(), Toast.LENGTH_SHORT).show();

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Patient> patList = realm.where(Patient.class).equalTo("doctor_id",doctor.getId()).findAll();

                Toast.makeText(getContext(), patList.size()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
