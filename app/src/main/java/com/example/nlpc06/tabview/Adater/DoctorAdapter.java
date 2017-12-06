package com.example.nlpc06.tabview.Adater;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nlpc06.tabview.Listener.DoctorClickListener;
import com.example.nlpc06.tabview.Model.Doctor;
import com.example.nlpc06.tabview.Model.Patient;
import com.example.nlpc06.tabview.R;
import com.example.nlpc06.tabview.Utility.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NL PC 06 on 12/6/2017.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorHolder> {

    private Context context;
    private List<Doctor> doctorList;
    private LayoutInflater inflater;

    private DoctorClickListener listener;

    public DoctorAdapter(Context context) {
        this.context = context;
        doctorList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DoctorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_doctor,parent,false);
        DoctorHolder holder = new DoctorHolder(view);
        return holder;
    }

    public void setDoctorClickListener(DoctorClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(DoctorHolder holder, int position) {

        Doctor doctor = doctorList.get(position);

        if(!TextUtils.isEmpty(doctor.getImagePath())){
            String path = doctor.getImagePath();
            Log.d("HHH",path);

            Bitmap bitmap = MyUtils.getBitmap(path);

            holder.ivImage.setImageBitmap(bitmap);
        }

        holder.tvName.setText(doctor.getName());
        holder.tvSpeciality.setText(doctor.getSpeciality());

    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public void addDoctor(Doctor doctor){
        doctorList.add(doctor);
        int position = doctorList.indexOf(doctor);
        notifyItemInserted(position);
    }

    public class DoctorHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivImage;
        TextView tvSpeciality,tvName;

        public DoctorHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvSpeciality = itemView.findViewById(R.id.tv_speciality);
            ivImage = itemView.findViewById(R.id.iv_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(listener!=null){
                listener.onDoctorClick(doctorList.get(getAdapterPosition()));
            }

        }
    }
}
