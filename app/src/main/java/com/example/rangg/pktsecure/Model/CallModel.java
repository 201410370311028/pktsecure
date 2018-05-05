package com.example.rangg.pktsecure.Model;

/**
 * Created by rangg on 11/03/2018.
 */

public class CallModel {

    private String katagori, subkatagori, nomor;

    public CallModel(String katagori, String subkatagori, String nomor) {
        this.katagori = katagori;
        this.subkatagori = subkatagori;
        this.nomor = nomor;
    }

    public String getKatagori() {
        return katagori;
    }

    public String getSubkatagori() {
        return subkatagori;
    }

    public String getNomor() {
        return nomor;
    }
}
