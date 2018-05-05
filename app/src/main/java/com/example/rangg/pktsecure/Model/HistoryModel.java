package com.example.rangg.pktsecure.Model;

/**
 * Created by rangg on 22/03/2018.
 */

public class HistoryModel {
    public String ID, NPKPelapor, Judul, Lokasi, Status;

    public HistoryModel(String ID, String NPKPelapor, String judul, String lokasi, String status) {
        this.ID = ID;
        this.NPKPelapor = NPKPelapor;
        Judul = judul;
        Lokasi = lokasi;
        Status = status;
    }

    public String getID() {
        return ID;
    }

    public String getNPKPelapor() {
        return NPKPelapor;
    }

    public String getJudul() {
        return Judul;
    }

    public String getLokasi() {
        return Lokasi;
    }

    public String getStatus() {
        return Status;
    }
}
