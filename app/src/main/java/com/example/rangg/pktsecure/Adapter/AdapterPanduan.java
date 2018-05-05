package com.example.rangg.pktsecure.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rangg.pktsecure.Model.PanduanModel;
import com.example.rangg.pktsecure.R;


import java.util.List;


public class AdapterPanduan extends RecyclerView.Adapter<AdapterPanduan.ViewHolder> {
    private List<PanduanModel> listPanduan;
    private Context context;

    public AdapterPanduan(Context context, List<PanduanModel> listPengaduan) {
        this.context = context;
        this.listPanduan = listPengaduan;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_panduan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PanduanModel aduan = listPanduan.get(position);
        holder.Judul.setText(aduan.getJudulPanduan());
//        holder.Created.setText(aduan.getCreated());
//        holder.Updated.setText(aduan.getUpdated());
        holder.Deskripsi.setText(aduan.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return listPanduan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Judul;
//        public TextView Created;
//        public TextView Updated;
        public TextView Deskripsi;


        public ViewHolder(View itemView) {
            super(itemView);

            Judul =(TextView)itemView.findViewById(R.id.Judul);
//            Created =(TextView)itemView.findViewById(R.id.created);
//            Updated =(TextView) itemView.findViewById(R.id.Updated);
            Deskripsi =(TextView) itemView.findViewById(R.id.Deskripsi);


        }
    }
}
