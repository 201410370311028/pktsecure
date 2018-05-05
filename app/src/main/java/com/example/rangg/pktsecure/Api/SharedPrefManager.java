package com.example.rangg.pktsecure.Api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.rangg.pktsecure.Model.PenggunaModel;
import com.example.rangg.pktsecure.Login.LoginActivity;


//here for this class we are using a singleton pattern

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "siskasharedpref";
    private static final String KEY_NPK = "keyenpk";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_HUBUNGAN = "keyhubungan";
    private static final String KEY_ALAMAT = "keyalamat";
    private static final String KEY_TELEPON = "keytelepon";
    private static final String KEY_ISAKTIF = "keyisaktif";
    private static final String KEY_ROLE = "keyrole";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(PenggunaModel user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NPK, user.getNPK());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_HUBUNGAN, user.getHubungan());
        editor.putString(KEY_ALAMAT, user.getAlamat());
        editor.putString(KEY_TELEPON, user.getTelepon());
        editor.putString(KEY_ISAKTIF, user.getHubungan());
        editor.putString(KEY_ROLE, user.getAlamat());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    public PenggunaModel getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PenggunaModel(
                sharedPreferences.getString(KEY_NPK, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_HUBUNGAN, null),
                sharedPreferences.getString(KEY_ALAMAT, null),
                sharedPreferences.getString(KEY_TELEPON, null),
                sharedPreferences.getString(KEY_ISAKTIF, null),
                sharedPreferences.getString(KEY_ROLE, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}