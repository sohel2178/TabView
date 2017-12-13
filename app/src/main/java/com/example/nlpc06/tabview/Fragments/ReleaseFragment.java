package com.example.nlpc06.tabview.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nlpc06.tabview.Adater.PatientAdapter;
import com.example.nlpc06.tabview.Adater.ReleaseAdapter;
import com.example.nlpc06.tabview.Listener.PatientClickListener;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.Model.Release;
import com.example.nlpc06.tabview.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReleaseFragment extends Fragment  {


    private RecyclerView rvPatient;

    private ReleaseAdapter adapter;


    public ReleaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ReleaseAdapter(getContext());

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Release> patList = realm.where(Release.class).findAll();

                Toast.makeText(getContext(), patList.size()+"", Toast.LENGTH_SHORT).show();

                for (Release x: patList){
                    adapter.addRelease(x);
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
        View view = inflater.inflate(R.layout.fragment_release, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvPatient = view.findViewById(R.id.rv_patient);
        rvPatient.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPatient.setAdapter(adapter);

    }

}
