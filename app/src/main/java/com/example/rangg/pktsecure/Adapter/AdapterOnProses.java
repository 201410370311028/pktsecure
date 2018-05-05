package com.example.rangg.pktsecure.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rangg.pktsecure.Model.PelaporanModel;
import com.example.rangg.pktsecure.Petugas.DetailPelaporanActivity;
import com.example.rangg.pktsecure.Petugas.PelengkapanDataActivity;
import com.example.rangg.pktsecure.R;

import java.util.List;

public class AdapterOnProses extends RecyclerView.Adapter<AdapterOnProses.ViewHolder> {

    private List<PelaporanModel> listTindak;
    private Context context;
    final int REQUEST_PHONE_CALL = 1;

    public AdapterOnProses(List<PelaporanModel> listTindak, Context context) {
        this.listTindak = listTindak;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_pelaporan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PelaporanModel aduan = listTindak.get(position);
        holder.viewID.setText(aduan.getID());
        holder.viewJudul.setText(aduan.getJudul());
        holder.viewLokasi.setText(aduan.getLokasi());
        holder.viewNotelepon.setText(aduan.getNoTelepon());
        holder.pelaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"ke klik cuyyy "+ aduan.getJudul(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, DetailPelaporanActivity.class);
                intent.putExtra("ID" , aduan.getID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });

        holder.telepon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+aduan.getNoTelepon()));
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
        return listTindak.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView viewJudul,viewID,viewLokasi, viewNotelepon;
        public ImageButton telepon, pelaporan;

        public ViewHolder(View itemView) {
            super(itemView);
            viewID = (TextView) itemView.findViewById(R.id.IDTindakan);
            viewJudul =(TextView)itemView.findViewById(R.id.judul_tindakan);
            viewLokasi =(TextView) itemView.findViewById(R.id.lokasi_tindakan);
            viewNotelepon = (TextView) itemView.findViewById(R.id.notelepon_tindakan);

            pelaporan = (ImageButton)itemView.findViewById(R.id.update);
            telepon = (ImageButton)itemView.findViewById(R.id.call);
        }
    }
}
