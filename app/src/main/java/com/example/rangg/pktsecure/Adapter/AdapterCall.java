package com.example.rangg.pktsecure.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rangg.pktsecure.Model.CallModel;
import com.example.rangg.pktsecure.R;

import java.util.List;


public class AdapterCall extends RecyclerView.Adapter<AdapterCall.ViewHolder> {

    private List<CallModel> listnomor;
    private Context context;
    final int REQUEST_PHONE_CALL = 1;

    public AdapterCall(List<CallModel> listnomor, Context context) {
        this.listnomor = listnomor;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_call, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CallModel Num = listnomor.get(position);
        holder.kategorinomor.setText(Num.getKatagori());
        holder.detailnomor.setText(Num.getNomor());

        holder.telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + Num.getNomor()));
                if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                }else{
                    context.startActivity(callIntent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listnomor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView kategorinomor,detailnomor;
        public ImageButton telepon;

        public ViewHolder(View itemView) {
            super(itemView);
            kategorinomor =(TextView) itemView.findViewById(R.id.telepon);
            detailnomor =(TextView)itemView.findViewById(R.id.detail);

            telepon = (ImageButton) itemView.findViewById(R.id.call);
        }
    }
}
