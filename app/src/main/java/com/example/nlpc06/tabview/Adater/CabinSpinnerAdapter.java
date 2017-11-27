package com.example.nlpc06.tabview.Adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NL PC 06 on 11/27/2017.
 */

public class CabinSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Cabin> cabinList;
    private LayoutInflater inflater;

    public CabinSpinnerAdapter(Context context) {
        this.context = context;
        cabinList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cabinList.size();
    }

    @Override
    public Object getItem(int position) {
        return cabinList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cabinList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.single_sp_view,parent,false);

        TextView textView = view.findViewById(R.id.text);
        textView.setText(cabinList.get(position).getCabin_num());

        return view;
    }


    public void addCabin(Cabin cabin){
        cabinList.add(cabin);
        notifyDataSetChanged();
    }
}
