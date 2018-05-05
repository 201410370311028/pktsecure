package com.example.rangg.pktsecure.Model;

/**
 * Created by rangg on 02/03/2018.
 */

public class PanduanModel {

    private String Judul, Deskripsi;

    public PanduanModel(String Judul, String Deskripsi) {
        this.Judul = Judul;
//        this.Created = Created;
//        this.Updated = Updated;
        this.Deskripsi = Deskripsi;


    }


    public String getJudulPanduan() {
        return Judul;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

//    public String getCreated() {
//        return Created;
//    }
//
//    public String getUpdated() {
//        return Updated;
//    }
}
