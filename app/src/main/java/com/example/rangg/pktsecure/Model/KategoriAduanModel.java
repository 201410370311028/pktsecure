package com.example.rangg.pktsecure.Model;

/**
 * Created by rangg on 21/03/2018.
 */

public class KategoriAduanModel {
    private String Kategori, ID;


    public KategoriAduanModel(String kategori, String ID) {
        Kategori = kategori;
        ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID() {
        this.ID = ID;
    }

    public void setKategori(String kategori) {
        Kategori = kategori;
    }

    public String getKategori() {
        return Kategori;
    }


}
