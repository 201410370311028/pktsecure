package com.example.rangg.pktsecure.Model;

/**
 * Created by rangg on 23/03/2018.
 */

public class ReadPengaduanModel {
    public String ID, NAMAPelapor, NPKPelapor, NoPengaduan, Judul, KategoriAduan, Lokasi, NoTelepon, Waktu, Tindakan, Keterangan, Status;

    public ReadPengaduanModel(){}

    public ReadPengaduanModel(String ID, String NAMAPelapor, String NPKPelapor, String noPengaduan, String judul, String kategoriAduan, String lokasi, String noTelepon, String waktu, String tindakan, String Keterangan, String status) {
        this.ID = ID;
        this.NAMAPelapor = NAMAPelapor;
        this.NPKPelapor = NPKPelapor;
        NoPengaduan = noPengaduan;
        Judul = judul;
        KategoriAduan = kategoriAduan;
        Lokasi = lokasi;
        NoTelepon = noTelepon;
        Waktu = waktu;
        Tindakan = tindakan;
        Keterangan = Keterangan;
        Status = status;
    }

    public String getID() {
        return ID;
    }

    public String getNAMAPelapor() {
        return NAMAPelapor;
    }

    public String getNPKPelapor() {
        return NPKPelapor;
    }

    public String getNoPengaduan() {
        return NoPengaduan;
    }

    public String getJudul() {
        return Judul;
    }

    public String getKategoriAduan() {
        return KategoriAduan;
    }

    public String getLokasi() {
        return Lokasi;
    }

    public String getNoTelepon() {
        return NoTelepon;
    }

    public String getWaktu() {
        return Waktu;
    }

    public String getTindakan() {
        return Tindakan;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public String getStatus() {
        return Status;
    }

}
