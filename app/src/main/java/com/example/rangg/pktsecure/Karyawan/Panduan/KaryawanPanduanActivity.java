package com.example.rangg.pktsecure.Karyawan.Panduan;

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

import com.example.rangg.pktsecure.Adapter.AdapterPanduan;
import com.example.rangg.pktsecure.Api.Api;
import com.example.rangg.pktsecure.Api.SharedPrefManager;
import com.example.rangg.pktsecure.Karyawan.Pengaduan.HistoryKaryawanActivity;
import com.example.rangg.pktsecure.Karyawan.Pengaduan.MenuKaryawanActivity;
import com.example.rangg.pktsecure.Model.PanduanModel;
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

public class KaryawanPanduanActivity extends AppCompatActivity {

    private RecyclerView rev;
    private RecyclerView.Adapter adapterPanduan;
    private List<PanduanModel> listpanduan;
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan);

        rev = (RecyclerView)findViewById(R.id.panduan);
        rev.setHasFixedSize(true);
        rev.setLayoutManager(new LinearLayoutManager(this));

        listpanduan = new ArrayList<>();

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
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("History Pengaduan").withIcon(getResources().getDrawable(R.drawable.history)));
        navigationDrawerLeft.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(KaryawanPanduanActivity.this, MenuKaryawanActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(KaryawanPanduanActivity.this, HistoryKaryawanActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        Log.d("Test","Default");
                        break;
                }
            }
        });

    }

    private void loadRecyleriewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.URL_PANDUAN,
                new Response.Listener<String>() {
                    public void onResponse(String s) {

                        try {
                            Log.v("response", String.valueOf(s));
                            JSONObject panduan = new JSONObject(s);
                            JSONArray panduanJSONArray = panduan.getJSONArray("array_panduan");
                            for(int i =0; i<panduanJSONArray.length(); i++){
                                JSONObject obj = panduanJSONArray.getJSONObject(i);

                                String JudulPanduan = obj.getString("Judul");
//                                String Created = obj.getString("Created");
//                                String Updated = obj.getString("Updated");
                                String Deskripsi = obj.getString("Deskripsi");
                                PanduanModel Panduann = new PanduanModel(JudulPanduan, Deskripsi);
                                listpanduan.add(Panduann);
                            }
                            adapterPanduan = new AdapterPanduan(KaryawanPanduanActivity.this, listpanduan);
                            rev.setAdapter(adapterPanduan);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KaryawanPanduanActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
