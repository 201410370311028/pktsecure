package com.example.rangg.pktsecure.Adapter;


import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rangg.pktsecure.Karyawan.Pengaduan.ReadPengaduanKaryawanActivity;
import com.example.rangg.pktsecure.Model.HistoryModel;
import com.example.rangg.pktsecure.R;

import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    private List<HistoryModel> historymodel;
    private Context context;

    public HistoryAdapter(Context context, List<HistoryModel> historymodel) {
        this.context = context;
        this.historymodel = historymodel;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final HistoryModel arrayHistory = historymodel.get(position);

        holder.viewID.setText(arrayHistory.getID());
        holder.viewNPKPelapor.setText(arrayHistory.getNPKPelapor());
        holder.viewJudul.setText(arrayHistory.getJudul());
        holder.viewLokasi.setText(arrayHistory.getLokasi());
        holder.viewStatus.setText(arrayHistory.getStatus());
        holder.button_view_detail_pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"ke klik cuyyy "+ arrayHistory.getJudul(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ReadPengaduanKaryawanActivity.class);
                intent.putExtra("ID" , arrayHistory.getID());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return historymodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView viewID;
        public TextView viewNPKPelapor;
        public TextView viewJudul;
        public TextView viewLokasi;
        public TextView viewStatus;

        public ImageButton button_view_detail_pengaduan;

        public ViewHolder(View itemView) {
            super(itemView);
            viewID =(TextView)itemView.findViewById(R.id.viewHistoryID);
            viewNPKPelapor =(TextView)itemView.findViewById(R.id.viewHistoryNPKPelapor);
            viewJudul =(TextView)itemView.findViewById(R.id.viewHistoryJudul);
            viewLokasi =(TextView) itemView.findViewById(R.id.viewHistoryLokasi);
            viewStatus = (TextView) itemView.findViewById(R.id.viewHistoryStatus);
            button_view_detail_pengaduan =(ImageButton) itemView.findViewById(R.id.buttonViewDetailPengaduan );
        }
    }
}
