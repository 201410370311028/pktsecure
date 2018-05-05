package com.example.rangg.pktsecure.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rangg.pktsecure.Model.HistoryModel;
import com.example.rangg.pktsecure.Model.ReadPengaduanModel;
import com.example.rangg.pktsecure.Petugas.DetailPelaporanActivity;
import com.example.rangg.pktsecure.Petugas.PelaporanPetugasActivity;
import com.example.rangg.pktsecure.R;

import java.util.List;


public class DetailPengaduanAdapter extends RecyclerView.Adapter<DetailPengaduanAdapter.ViewHolder> {


    private List<ReadPengaduanModel> readrengaduanmodel;
    private Context context;

    public DetailPengaduanAdapter(Context context, List<ReadPengaduanModel> readrengaduanmodel) {
        this.context = context;
        this.readrengaduanmodel = readrengaduanmodel;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_pengaduan_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ReadPengaduanModel arrayReadPengaduan = readrengaduanmodel.get(position);

        holder.viewID.setText(arrayReadPengaduan.getID());
        holder.viewNAMAPelapor.setText(arrayReadPengaduan.getNPKPelapor());
        holder.viewNPKPelapor.setText(arrayReadPengaduan.getNAMAPelapor());
        holder.viewNoPengaduan.setText(arrayReadPengaduan.getNoPengaduan());
        holder.viewJudul.setText(arrayReadPengaduan.getJudul());
        holder.viewKategoriAduan.setText(arrayReadPengaduan.getKategoriAduan());
        holder.viewLokasi.setText(arrayReadPengaduan.getLokasi());
        holder.viewNoTelepon.setText(arrayReadPengaduan.getNoTelepon());
        holder.viewWaktu.setText(arrayReadPengaduan.getWaktu());
        holder.viewTindakan.setText(arrayReadPengaduan.getTindakan());
        holder.Keterangan.setText(arrayReadPengaduan.getKeterangan());
        holder.viewStatus.setText(arrayReadPengaduan.getStatus());

        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"ke klik cuyyy "+ arrayReadPengaduan.getJudul(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, PelaporanPetugasActivity.class);
                intent.putExtra("ID" , arrayReadPengaduan.getID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return readrengaduanmodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView viewID;
        public TextView viewNAMAPelapor;
        public TextView viewNPKPelapor;
        public TextView viewNoPengaduan;
        public TextView viewJudul;
        public TextView viewKategoriAduan;
        public TextView viewLokasi;
        public TextView viewNoTelepon;
        public TextView viewWaktu;
        public TextView viewTindakan;
        public TextView Keterangan;
        public TextView viewStatus;

        public Button next;


        public ViewHolder(View itemView) {
            super(itemView);
            viewID =(TextView)itemView.findViewById(R.id.viewReadID);
            viewNAMAPelapor =(TextView)itemView.findViewById(R.id.viewReadNamapelapor);
            viewNPKPelapor =(TextView)itemView.findViewById(R.id.viewReadNPKPelapor);
            viewNoPengaduan =(TextView)itemView.findViewById(R.id.viewReadNoPengaduan);
            viewJudul =(TextView)itemView.findViewById(R.id.viewReadJudul);
            viewKategoriAduan =(TextView)itemView.findViewById(R.id.viewReadKategoriAduan);
            viewLokasi =(TextView) itemView.findViewById(R.id.viewReadLokasi);
            viewNoTelepon =(TextView) itemView.findViewById(R.id.viewReadNoTelepon);
            viewWaktu =(TextView) itemView.findViewById(R.id.viewReadWaktu);
            viewTindakan =(TextView) itemView.findViewById(R.id.viewReadTindakan);
            Keterangan = (TextView) itemView.findViewById(R.id.Keterangan);
            viewStatus = (TextView) itemView.findViewById(R.id.viewReadStatus);
            next = (Button) itemView.findViewById(R.id.next);

        }
    }
}
