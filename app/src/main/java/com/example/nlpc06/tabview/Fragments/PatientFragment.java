package com.example.nlpc06.tabview.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nlpc06.tabview.Activities.AddPatientActivity;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientFragment extends Fragment implements View.OnClickListener{
    private static final int REQ_CODE=1452;


    private FloatingActionButton fabAdd;
    private RecyclerView rvPatient;


    public PatientFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Patient> patList = realm.where(Patient.class).findAll();

                Log.d("LLLL",patList.get(0).getName());
                Log.d("LLLL",patList.get(0).getAddress());
                Log.d("LLLL",patList.get(0).getContact_number());
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

            }
        }
    }
}
