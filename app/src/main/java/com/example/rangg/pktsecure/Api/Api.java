package com.example.rangg.pktsecure.Api;

/**
 * Created by rangg on 01/03/2018.
 */

public class Api {
    private static final String ROOT_URL="http://192.168.1.12/pktsecure/v1/api.php?apicall=";

    public static final String URL_LOGIN = ROOT_URL +"login";

    public static final String URL_CREATE_PENGADUAN = ROOT_URL +"create_pengaduan";
    public static final String URL_VL_KATEGORI_ADUAN = ROOT_URL + "vl_kategoriaduan";

    public static final String URL_READ_PENGADUAN = ROOT_URL +"read_list_pengaduan";
    public static final String URL_CANCEL_PENGADUAN = ROOT_URL +"cancel_pengaduan";

    public static final String URL_READ_DETAIL_PENGADUAN = ROOT_URL +"read_pelaporan";
    public static final String URL_CALL_CENTER = ROOT_URL +"call_center";
    public static final String URL_PANDUAN = ROOT_URL +"panduan";

    public static final String URL_READ_PENGADUAN_PETUGAS = ROOT_URL +"read_status_onproses";
    public static final String URL_READ_VERIFIKASI_PETUGAS = ROOT_URL +"read_status_verifikasi";
    public static final String URL_READ_CLOSED_PETUGAS = ROOT_URL +"read_status_close";

    public static final String URL_UPDATE_PENGADUAN = ROOT_URL+"update_pelaporan";
    public static final String URL_STATUS = ROOT_URL+"update_status";





}
