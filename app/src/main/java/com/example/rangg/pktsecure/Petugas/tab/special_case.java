package com.example.rangg.pktsecure.Petugas.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rangg.pktsecure.Adapter.AdapterOnProses;
import com.example.rangg.pktsecure.Api.Api;
import com.example.rangg.pktsecure.Api.SharedPrefManager;
import com.example.rangg.pktsecure.Model.PelaporanModel;
import com.example.rangg.pktsecure.Model.PenggunaModel;
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
import java.util.List;

/**
 * Created by rangg on 26/02/2018.
 */

public class special_case extends AppCompatActivity {

    private RecyclerView rev;
    private RecyclerView.Adapter AdapterTindakan;
    private List<PelaporanModel> listTindak;
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_case_activity);

        rev = (RecyclerView)findViewById(R.id.on_proses);
        rev.setHasFixedSize(true);
        rev.setLayoutManager(new LinearLayoutManager(this));

        listTindak = new ArrayList<>();

        loadRecyleriewData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("PKT SECURE");

        // toolbar.setTitleTextColor();
        setSupportActionBar(toolbar);
        PenggunaModel user = SharedPrefManager.getInstance(this).getUser();


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
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("History Pengaduan").withIcon(getResources().getDrawable(R.drawable.home)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Menu Petugas").withIcon(getResources().getDrawable(R.drawable.home)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Tanggap Cepat").withIcon(getResources().getDrawable(R.drawable.history)));
        navigationDrawerLeft.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(special_case.this, special_case.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(special_case.this, special_case.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(special_case.this, special_case.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(special_case.this, special_case.class);
                        startActivity(intent);
                        break;
                    default:
                        Log.d("Test","Default");
                        break;
                }
            }
        });
    }

    public void button_on_proses(View view) {
        Intent proses = new Intent(special_case.this, on_proses.class);
        startActivity(proses);
    }

    public void button_special_key(View view) {
        Intent spesial = new Intent(special_case.this, special_case.class);
        startActivity(spesial);
    }

    public void button_verifikasi(View view){
        Intent verifikasi = new Intent(special_case.this, special_case.class);
        startActivity(verifikasi);
    }

    public void button_closed(View view){
        Intent verifikasi = new Intent(special_case.this, close.class);
        startActivity(verifikasi);
    }

    private void loadRecyleriewData() {
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("loading data..");
//        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.URL_READ_PENGADUAN_PETUGAS,
                new Response.Listener<String>() {
                    public void onResponse(String response) {

                        try {
                            Log.v("response", String.valueOf(response));
                            JSONObject aduan = new JSONObject(response);
                            JSONArray aduanarr = aduan.getJSONArray("array_status");
                            for(int i =0; i<aduanarr.length(); i++){
                                JSONObject obj = aduanarr.getJSONObject(i);
                                String ID = obj.getString("ID");
                                String Judul = obj.getString("Judul");
                                String Lokasi = obj.getString("Lokasi");
                                String NoTelepon = obj.getString("NoTelepon");
                                PelaporanModel pengaduan = new PelaporanModel(ID, Judul, Lokasi, NoTelepon);
                                listTindak.add(pengaduan);
                            }

                            AdapterTindakan = new AdapterOnProses(listTindak, special_case.this);
                            rev.setAdapter(AdapterTindakan);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(special_case.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
