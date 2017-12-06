package com.example.nlpc06.tabview.Adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.Model.Doctor;
import com.example.nlpc06.tabview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NL PC 06 on 12/6/2017.
 */

public class DoctorSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<Doctor> doctorList;
    private LayoutInflater inflater;

    public DoctorSpinnerAdapter(Context context) {
        this.context = context;
        doctorList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return doctorList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.single_sp_view,parent,false);

        TextView textView = view.findViewById(R.id.text);
        textView.setText(doctorList.get(position).getName());

        return view;
    }

    public void addDoctor(Doctor doctor){
        doctorList.add(doctor);
        notifyDataSetChanged();
    }
}
