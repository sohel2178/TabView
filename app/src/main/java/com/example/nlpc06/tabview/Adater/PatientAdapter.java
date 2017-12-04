package com.example.nlpc06.tabview.Adater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nlpc06.tabview.Listener.PatientClickListener;
import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by NL PC 06 on 11/30/2017.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder>{
    private Context context;
    private List<Patient> patientList;
    private LayoutInflater inflater;

    private PatientClickListener listener;

    public PatientAdapter(Context context) {
        this.context = context;
        patientList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PatientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_patient,parent,false);
        PatientHolder holder = new PatientHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PatientHolder holder, int position) {
        Patient patient = patientList.get(position);

        if(!TextUtils.isEmpty(patient.getImagePath())){
            String path = patient.getImagePath();
            Log.d("HHH",path);

            Bitmap bitmap = getBitmap(path);

            holder.ivImage.setImageBitmap(bitmap);
        }

        holder.tvPatientName.setText(patient.getName());
        holder.tvPatientPhoneNumber.setText(patient.getContact_number());

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }


    public void setPatientClickListener(PatientClickListener listener){
        this.listener =listener;
    }


    public void addPatient(Patient patient){
        patientList.add(patient);
        int position = patientList.indexOf(patient);
        notifyItemInserted(position);
    }


    public class PatientHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvPatientName,tvPatientPhoneNumber;
        ImageView ivImage;

        public PatientHolder(View itemView) {
            super(itemView);
            tvPatientName = itemView.findViewById(R.id.tv_patient_name);
            tvPatientPhoneNumber = itemView.findViewById(R.id.patient_phone_number);
            ivImage = itemView.findViewById(R.id.iv_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(context, "Item View Clicked "+getAdapterPosition(), Toast.LENGTH_SHORT).show();

            if(listener!= null){
                listener.patientClick(patientList.get(getAdapterPosition()));
            }

        }
    }


    private Bitmap getBitmap(String path){
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(path,bmOptions);
        //bitmap = Bitmap.createScaledBitmap(bitmap,120,120,true);
        return bitmap;
    }
}
