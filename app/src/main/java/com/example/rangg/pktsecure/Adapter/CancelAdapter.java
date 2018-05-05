package com.example.rangg.pktsecure.Adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.rangg.pktsecure.Model.CancelModel;
import com.example.rangg.pktsecure.R;

import java.util.List;


public class CancelAdapter extends RecyclerView.Adapter<CancelAdapter.ViewHolder> {

    final int REQUEST_PHONE_CALL = 1;

    private List<CancelModel> cancelmodel;
    private Context context;

    public CancelAdapter(Context context, List<CancelModel> cancelmodel) {
        this.context = context;
        this.cancelmodel = cancelmodel;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final CancelModel arrayCancel = cancelmodel.get(position);

        holder.button_cancel_pengaduan.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setTitle("Membatalkan Pengaduan");

                alertDialog.setTitle("Apakah Anda Yakin Untuk Membatalkan Pengaduan ?");

                alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + arrayCancel.getNomor()));
                        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                        }else{
                            context.startActivity(callIntent);
                        }
                    }
                });

                alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return cancelmodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageButton button_cancel_pengaduan;

        public ViewHolder(View itemView) {
            super(itemView);
            button_cancel_pengaduan =(ImageButton) itemView.findViewById(R.id.buttonCancelPengaduan );
        }
    }
}
