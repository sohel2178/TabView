package com.example.nlpc06.tabview.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nlpc06.tabview.Activities.AddPatientActivity;
import com.example.nlpc06.tabview.Activities.PatientDetailActivity;
import com.example.nlpc06.tabview.Adater.PatientAdapter;
import com.example.nlpc06.tabview.Listener.PatientClickListener;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientFragment extends Fragment implements View.OnClickListener,PatientClickListener{
    private static final int REQ_CODE=1452;
    private static final int RELEASE_CODE=1453;


    private FloatingActionButton fabAdd;
    private RecyclerView rvPatient;

    private PatientAdapter adapter;


    public PatientFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new PatientAdapter(getContext());
        adapter.setPatientClickListener(this);

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Patient> patList = realm.where(Patient.class).findAll();

                Toast.makeText(getContext(), patList.size()+"", Toast.LENGTH_SHORT).show();

                for (Patient x: patList){
                    adapter.addPatient(x);
                }

               /* Log.d("LLLL",patList.get(0).getName());
                Log.d("LLLL",patList.get(0).getAddress());
                Log.d("LLLL",patList.get(0).getContact_number());*/
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fabAdd = view.findViewById(R.id.button);
        rvPatient = view.findViewById(R.id.rv_patient);
        rvPatient.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPatient.setAdapter(adapter);

        fabAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), AddPatientActivity.class);
        startActivityForResult(intent,REQ_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_CODE){
            if(resultCode==getActivity().RESULT_OK){

                Patient patient = (Patient) data.getSerializableExtra("patient");

               /* int id = data.getIntExtra("id",-1);
                int age = data.getIntExtra("age",-1);
                int cabin_id = data.getIntExtra("cabin_id",-1);
                String name = data.getStringExtra("name");
                String address = data.getStringExtra("address");
                String contact = data.getStringExtra("contact");
                String image = data.getStringExtra("image");

                Patient patient = new Patient(id,name,age,address,cabin_id,contact);
                patient.setImagePath(image);*/

                adapter.addPatient(patient);


            }
        }

        if(requestCode==RELEASE_CODE && resultCode==getActivity().RESULT_OK){

            int id = data.getIntExtra("patId",-1);

            if(id!=-1){
                adapter.removePatient(id);
            }



        }
    }

    @Override
    public void patientClick(Patient patient) {
        //Toast.makeText(getContext(), patient.getName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), PatientDetailActivity.class);
        intent.putExtra("id",patient.getId());
        startActivityForResult(intent,RELEASE_CODE);

    }
}
