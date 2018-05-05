package com.example.rangg.pktsecure.Karyawan.Pengaduan;

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
import com.example.rangg.pktsecure.Adapter.ReadPengaduanAdapter;
import com.example.rangg.pktsecure.Api.Api;
import com.example.rangg.pktsecure.Api.SharedPrefManager;
import com.example.rangg.pktsecure.Model.PenggunaModel;
import com.example.rangg.pktsecure.Model.ReadPengaduanModel;
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
import java.util.Map;

/**
 * Created by rangg on 22/03/2018.
 */

public class ReadPengaduanKaryawanActivity extends AppCompatActivity {
    private RecyclerView rev;
    private RecyclerView.Adapter adapterreadpengaduan;
    private List<ReadPengaduanModel> readpengaduanmodel;
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pengaduan);

        rev = (RecyclerView)findViewById(R.id.read_pengaduan);
        rev.setHasFixedSize(false);
        rev.setLayoutManager(new LinearLayoutManager(ReadPengaduanKaryawanActivity.this));

        readpengaduanmodel = new ArrayList<>();

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
                        intent = new Intent(ReadPengaduanKaryawanActivity.this, MenuKaryawanActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(ReadPengaduanKaryawanActivity.this, HistoryKaryawanActivity.class);
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_READ_DETAIL_PENGADUAN,
                new Response.Listener<String>() {
                    public void onResponse(String response) {

                        try {
                            Log.v("response", String.valueOf(response));
                            JSONObject historyobj = new JSONObject(response);
                            JSONArray historyarr = historyobj.getJSONArray("array_pelaporan");

                            for(int i =0; i<historyarr.length(); i++){
                                JSONObject obj = historyarr.getJSONObject(i);
                                String ID = obj.getString("ID");
                                String NamaPelapor = obj.getString("NamaPelapor");
                                String NPKPelapor = obj.getString("NPKPelapor");
                                String NoPengaduan = obj.getString("NoPengaduan");
                                String Judul = obj.getString("Judul");
                                String KategoriAduan = obj.getString("KategoriAduan");
                                String Lokasi = obj.getString("Lokasi");
                                String NoTelepon = obj.getString("NoTelepon");
                                String Waktu = obj.getString("Waktu");
                                String Tindakan = obj.getString("Tindakan");
                                String Keterangan = obj.getString("Keterangan");
                                String Status = obj.getString("Status");
                                ReadPengaduanModel pengaduan = new ReadPengaduanModel(ID, NamaPelapor, NPKPelapor, NoPengaduan, Judul, KategoriAduan, Lokasi, NoTelepon, Waktu, Tindakan, Keterangan, Status);
                                readpengaduanmodel.add(pengaduan);
                            }
                            adapterreadpengaduan = new ReadPengaduanAdapter(ReadPengaduanKaryawanActivity.this, readpengaduanmodel);
                            rev.setAdapter(adapterreadpengaduan);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReadPengaduanKaryawanActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String,String> getParams(){
                HashMap<String, String> params = new HashMap<>();

                Bundle extras = getIntent().getExtras();

                String id= extras.getString("ID");
                params.put("ID", id);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

}
