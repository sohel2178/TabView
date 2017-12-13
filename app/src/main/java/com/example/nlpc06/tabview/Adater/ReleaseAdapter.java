package com.example.nlpc06.tabview.Adater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nlpc06.tabview.Listener.PatientClickListener;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.Model.Release;
import com.example.nlpc06.tabview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NL PC 06 on 11/30/2017.
 */

public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.PatientHolder>{
    private Context context;
    private List<Release> releaseList;
    private LayoutInflater inflater;

    private boolean circular;

    public ReleaseAdapter(Context context) {
        this.context = context;
        releaseList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    public ReleaseAdapter(Context context, boolean circular) {
        this.context = context;
        releaseList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.circular = circular;
    }

    @Override
    public PatientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        if(viewType==1){
            view = inflater.inflate(R.layout.single_patient_2,parent,false);
        }else{
            view = inflater.inflate(R.layout.single_patient,parent,false);
        }


        //View view = inflater.inflate(R.layout.single_patient,parent,false);
        PatientHolder holder = new PatientHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PatientHolder holder, int position) {
        Release patient = releaseList.get(position);

        if(!TextUtils.isEmpty(patient.getImagePath())){
            String path = patient.getImagePath();
            Log.d("HHH",path);

            Bitmap bitmap = getBitmap(path);

            holder.ivImage.setImageBitmap(bitmap);
        }

        holder.tvPatientName.setText(patient.getName());

        if(!circular){
            holder.tvPatientPhoneNumber.setText(patient.getContact_number());
        }



    }

    @Override
    public int getItemCount() {
        return releaseList.size();
    }


    @Override
    public int getItemViewType(int position) {

        if(circular){
            return 1;
        }else {
            return 2;
        }
    }

   /* public void setPatientClickListener(PatientClickListener listener){
        this.listener =listener;
    }*/


    public void addRelease(Release patient){
        releaseList.add(patient);
        int position = releaseList.indexOf(patient);
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

           /* if(listener!= null){
                listener.patientClick(releaseList.get(getAdapterPosition()));
            }*/

        }
    }


    private Bitmap getBitmap(String path){
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(path,bmOptions);
        //bitmap = Bitmap.createScaledBitmap(bitmap,120,120,true);
        return bitmap;
    }
}
