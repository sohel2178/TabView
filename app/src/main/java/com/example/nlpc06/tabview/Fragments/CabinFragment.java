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

import com.example.nlpc06.tabview.Activities.AddCabin;
import com.example.nlpc06.tabview.Adater.CabinAdapter;
import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.R;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class CabinFragment extends Fragment implements View.OnClickListener {
    private static final int REQ_CODE=123;

    private FloatingActionButton fabAdd;

    private RecyclerView rvCabin;

    private CabinAdapter adapter;


    public CabinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CabinAdapter(getContext());

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Cabin> cabinList = realm.where(Cabin.class).findAll();

                for(Cabin x: cabinList){
                    adapter.addCabin(x);
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cabin, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        fabAdd = view.findViewById(R.id.button);
        fabAdd.setOnClickListener(this);

        rvCabin = view.findViewById(R.id.rv_cabin);
        rvCabin.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCabin.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getContext(), AddCabin.class);
        startActivityForResult(intent,REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_CODE){
            if(resultCode==getActivity().RESULT_OK){
                int id = data.getIntExtra("id",-1);
                int type = data.getIntExtra("type",-1);
                int utility = data.getIntExtra("utility",-1);
                String name = data.getStringExtra("name");

                Cabin cabin = new Cabin(id,name,type,utility);

                adapter.addCabin(cabin);

                /*Log.d("JJJJJ",id+"");
                Log.d("JJJJJ",type+"");
                Log.d("JJJJJ",utility+"");
                Log.d("JJJJJ",name);*/
            }
        }
    }
}
