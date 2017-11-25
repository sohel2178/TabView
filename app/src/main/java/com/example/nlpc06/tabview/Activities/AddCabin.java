package com.example.nlpc06.tabview.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.R;

import io.realm.Realm;

public class AddCabin extends AppCompatActivity implements View.OnClickListener{
    private EditText etName;
    private Spinner spCabinType, spCabinUtility;
    private Button btnSubmit;

    String [] cabinType={"Single", "Double"};
    String [] cabinUtility={"Ac", "Non-Ac"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cabin);
        initView();


    }

    private void initView() {
        etName= (EditText) findViewById(R.id.et_name);
        spCabinType= (Spinner) findViewById(R.id.sp_cabin_type);
        spCabinUtility= (Spinner) findViewById(R.id.sp_cabin_utility);
        btnSubmit= (Button) findViewById(R.id.submit);

        btnSubmit.setOnClickListener(this);

        SpinnerAdapter typeAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,cabinType);
        SpinnerAdapter utilityAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,cabinUtility);

        spCabinType.setAdapter(typeAdapter);
        spCabinUtility.setAdapter(utilityAdapter);

    }

    @Override
    public void onClick(View v) {

        final int selType =spCabinType.getSelectedItemPosition();
        final int selUtility = spCabinUtility.getSelectedItemPosition();


        final String cabinName = etName.getText().toString();

        final Cabin cabin = new Cabin(cabinName,selType,selUtility);

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNum = realm.where(Cabin.class).max("id");

                int nextId;

                if(currentIdNum==null){
                    nextId =1;
                }else{
                    nextId = currentIdNum.intValue()+1;
                }

                cabin.setId(nextId);

                realm.insert(cabin);

                Intent intent = new Intent();
                intent.putExtra("id",nextId);
                intent.putExtra("name",cabinName);
                intent.putExtra("type",selType);
                intent.putExtra("utility",selUtility);

                setResult(RESULT_OK,intent);


                AddCabin.this.finish();
            }
        });

    }
}
