package com.example.nlpc06.tabview.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nlpc06.tabview.Adater.CabinSpinnerAdapter;
import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddPatientActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName,etAge,etAddress,etContact;
    private Spinner spCabin;
    private Button btnAdd;

    CabinSpinnerAdapter adapter;



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
                RealmResults<Cabin> cabinList = realm.where(Cabin.class).findAll();

                for (Cabin x:cabinList){
                    adapter.addCabin(x);
                }
            }
        });


        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        etAddress = (EditText) findViewById(R.id.et_address);
        etContact = (EditText) findViewById(R.id.et_contact);

        spCabin = (Spinner) findViewById(R.id.sp_select_cabin);
        spCabin.setAdapter(adapter);

        btnAdd = (Button) findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String ageStr = etAge.getText().toString();
        String address = etAddress.getText().toString();
        String contact = etContact.getText().toString();

        int age =0;

        if(!TextUtils.isEmpty(ageStr)){
            age = Integer.parseInt(ageStr);
        }


        int pos = spCabin.getSelectedItemPosition();

        int cabinId =(int) adapter.getItemId(pos);

        final Patient patient = new Patient(name,age,address,cabinId,contact);

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(patient);

                AddPatientActivity.this.finish();
            }
        });

    }
}
