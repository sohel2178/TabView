package com.example.nlpc06.tabview.Adater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nlpc06.tabview.Model.Cabin;
import com.example.nlpc06.tabview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NL PC 06 on 11/25/2017.
 */

public class CabinAdapter extends RecyclerView.Adapter<CabinAdapter.CabinHolder> {

    private Context context;
    private List<Cabin> cabinList;
    private LayoutInflater inflater;


    public CabinAdapter(Context context) {
        this.context = context;
        cabinList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }


    public void addCabin(Cabin cabin){
        cabinList.add(cabin);

        int position = cabinList.indexOf(cabin);

        notifyItemInserted(position);
    }

    @Override
    public CabinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_cabin,parent,false);

        CabinHolder cabinHolder = new CabinHolder(view);

        return cabinHolder;
    }

    @Override
    public void onBindViewHolder(CabinHolder holder, int position) {
        Cabin cabin = cabinList.get(position);

        holder.tvCabinName.setText(cabin.getCabin_num());

        if(cabin.getCabin_type()==0){
            holder.tvCabinType.setText("Single");
        }else{
            holder.tvCabinType.setText("Double");
        }

        if(cabin.getUtility()==0){
            holder.tvCabinUtility.setText("AC");
        }else{
            holder.tvCabinUtility.setText("NON-AC");
        }




    }

    @Override
    public int getItemCount() {
        return cabinList.size();
    }




    public class CabinHolder extends RecyclerView.ViewHolder{
        TextView tvCabinName,tvCabinType,tvCabinUtility;

        public CabinHolder(View itemView) {
            super(itemView);

            tvCabinName = itemView.findViewById(R.id.tv_cabin_name);
            tvCabinType = itemView.findViewById(R.id.tv_cabin_type);
            tvCabinUtility = itemView.findViewById(R.id.tv_cabin_utility);
        }
    }
}
