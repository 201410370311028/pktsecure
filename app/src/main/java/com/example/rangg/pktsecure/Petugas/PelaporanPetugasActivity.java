package com.example.rangg.pktsecure.Petugas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rangg.pktsecure.Adapter.KategoriAduanSpinnerAdapter;
import com.example.rangg.pktsecure.Api.RequestHandler;
import com.example.rangg.pktsecure.Api.SharedPrefManager;
import com.example.rangg.pktsecure.Api.Api;
import com.example.rangg.pktsecure.Karyawan.Pengaduan.HistoryKaryawanActivity;
import com.example.rangg.pktsecure.Karyawan.Pengaduan.MenuKaryawanActivity;
import com.example.rangg.pktsecure.Model.KategoriAduanModel;
import com.example.rangg.pktsecure.Model.PenggunaModel;
import com.example.rangg.pktsecure.Model.StatusModel;
import com.example.rangg.pktsecure.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by rangg on 01/03/2018.
 */

public class PelaporanPetugasActivity extends AppCompatActivity {

    private List<KategoriAduanModel> listKategori = new ArrayList<>();

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;

    EditText IDPengisian,DataFakta, Kronologi, Uraian;
    Spinner spinnerJenisAduan;

    Button buttonSubmit;

    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengisian_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("PKT SECURE");

        // toolbar.setTitleTextColor();
        setSupportActionBar(toolbar);
        PenggunaModel user = SharedPrefManager.getInstance(this).getUser();

        prepareKategoriAduanSpinner();

        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withEmail(user.getUsername())
                                .withName(user.getNPK())
                                .withIcon(getResources().getDrawable(R.drawable.username))
                )
                .build();

        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(headerNavigationLeft)
                .withSelectedItem(0)
                .build();

        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.drawable.home)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("History Pengaduan").withIcon(getResources().getDrawable(R.drawable.history)));

        navigationDrawerLeft.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(PelaporanPetugasActivity.this, MenuKaryawanActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(PelaporanPetugasActivity.this, HistoryKaryawanActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        Log.d("Test","Default");
                        break;
                }
            }
        });

        IDPengisian = (EditText) findViewById(R.id.IDPengisian);
        DataFakta = (EditText) findViewById(R.id.DataFakta);
        Kronologi = (EditText) findViewById(R.id.Kronologi);
        Uraian = (EditText) findViewById(R.id.Uraian);

        spinnerJenisAduan = (Spinner) findViewById(R.id.spinner_jenisaduan);

        final StatusModel aduan = new StatusModel();
        spinnerJenisAduan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonSubmit = (Button) findViewById(R.id.button_next);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdating) {
                    //updatepengaduan();
                } else {
                    pelaporan();
                    prepareKategoriAduanSpinner();
                    Intent intent = new Intent(PelaporanPetugasActivity.this, PelengkapanDataActivity.class);
                    IDPengisian.setText(aduan.getID());
                    intent.putExtra("ID",aduan.getID());
                    startActivity(intent);
                }
            }
        });

        updateTextLabel();

    }


    private void updateTextLabel() {
        Bundle extras = getIntent().getExtras();

        String id= extras.getString("ID");
        IDPengisian.setText(id);
    }

    private void prepareKategoriAduanSpinner(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.URL_VL_KATEGORI_ADUAN,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject aduan = new JSONObject(response);
                            JSONArray aduanarr = aduan.getJSONArray("array_kategoriaduan");
                            for(int i =0; i<aduanarr.length(); i++){
                                JSONObject obj = aduanarr.getJSONObject(i);
                                String Kategori = obj.getString("Kategori");
                                String IDKategori = obj.getString("ID");

                                KategoriAduanModel kategoriAduanModel = new KategoriAduanModel(Kategori, IDKategori);
                                listKategori.add(kategoriAduanModel);
                            }
                            KategoriAduanSpinnerAdapter kategoriAduanSpinnerAdapter = new KategoriAduanSpinnerAdapter(PelaporanPetugasActivity.this, listKategori);
                            spinnerJenisAduan.setAdapter(kategoriAduanSpinnerAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PelaporanPetugasActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void pelaporan() {

        String jenisaduan = spinnerJenisAduan.getSelectedItem().toString();
        String ID = IDPengisian.getText().toString().trim();
        String datafakta = DataFakta.getText().toString().trim();
        String kronologi = Kronologi.getText().toString().trim();
        String uraian = Uraian.getText().toString().trim();

        if (TextUtils.isEmpty(datafakta)) {
            DataFakta.setError("masukan data fakta");
            DataFakta.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(kronologi)) {
            Kronologi.setError("masukan kronologi pengaduan");
            Kronologi.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(uraian)) {
            Uraian.setError("masukan judul pengaduan");
            Uraian.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("ID", ID);
        params.put("JenisAduan", jenisaduan);
        params.put("DataFakta", datafakta);
        params.put("Kronologi", kronologi);
        params.put("Uraian", uraian);


        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_UPDATE_PENGADUAN, params, CODE_POST_REQUEST);
        request.execute();
    }

    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        String url;

        HashMap<String, String> params;

        int requestCode;

        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.v("erro",s);

            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            Log.v("erro","3");

            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return requestHandler.sendPostRequest(Api.URL_CREATE_PENGADUAN, params);

        }
    }

}
