package com.example.rangg.pktsecure.Model;

/**
 * Created by rangg on 22/03/2018.
 */

public class PelaporanModel {
    public String ID, Judul, Lokasi, NoTelepon;

    public PelaporanModel(String ID, String judul, String lokasi, String NoTelepon) {
        this.ID = ID;
//        this.BUJP = BUJP;
//        this.NPK= NPK;
        this.Judul = judul;
        this.Lokasi = lokasi;
//        this.Status = status;
        this.NoTelepon = NoTelepon;
    }

    public String getID() {
        return ID;
    }

//    public String getNPK() {
//        return NPK;
//    }

    public String getJudul() {
        return Judul;
    }

    public String getLokasi() {
        return Lokasi;
    }

//    public String getStatus() {
//        return Status;
//    }

//    public String getBUJP() {
//        return BUJP;
//    }

    public String getNoTelepon() {
        return NoTelepon;
    }
}
