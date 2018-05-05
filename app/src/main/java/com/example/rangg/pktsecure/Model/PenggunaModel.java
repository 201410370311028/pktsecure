package com.example.rangg.pktsecure.Model;

/**
 * Created by rangg on 01/03/2018.
 */

public class PenggunaModel {
    private String NPK, Username, Hubungan, Alamat, Telepon, IsAktif, Role;

    public PenggunaModel(String NPK, String username, String hubungan, String alamat, String telepon, String isAktif, String role) {
        this.NPK = NPK;
        Username = username;
        Hubungan = hubungan;
        Alamat = alamat;
        Telepon = telepon;
        IsAktif = isAktif;
        Role = role;

    }

    public String getNPK() {
        return NPK;
    }

    public String getUsername() {
        return Username;
    }

    public String getHubungan() {
        return Hubungan;
    }

    public String getAlamat() {
        return Alamat;
    }

    public String getTelepon() {
        return Telepon;
    }

    public String getIsAktif() {
        return IsAktif;
    }

    public String getRole() {
        return Role;
    }
}


