package com.example.rangg.pktsecure.Model;

/**
 * Created by rangg on 22/03/2018.
 */

public class CancelModel {
    public String Nomor;

    public CancelModel(String nomor) {
        Nomor = nomor;
//        Kategori = kategori;
    }

    public String getNomor() {
        return Nomor;
    }

//    public String getKategori() {
//        return Kategori;
//    }
}
