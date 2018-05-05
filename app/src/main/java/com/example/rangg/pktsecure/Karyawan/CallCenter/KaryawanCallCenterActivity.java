package com.example.rangg.pktsecure.Karyawan.CallCenter;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.rangg.pktsecure.Adapter.AdapterCall;
import com.example.rangg.pktsecure.Api.Api;
import com.example.rangg.pktsecure.Api.SharedPrefManager;
import com.example.rangg.pktsecure.Karyawan.Pengaduan.HistoryKaryawanActivity;
import com.example.rangg.pktsecure.Karyawan.Pengaduan.MenuKaryawanActivity;
import com.example.rangg.pktsecure.Model.CallModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rangg on 26/02/2018.
 */

public class KaryawanCallCenterActivity extends AppCompatActivity {

    private RecyclerView rev;
    private RecyclerView.Adapter AdapterCall;
    private List<CallModel> listnomor;
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_center);

        rev = (RecyclerView)findViewById(R.id.call);
        rev.setHasFixedSize(true);
        rev.setLayoutManager(new LinearLayoutManager(this));

        listnomor = new ArrayList<>();

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
                        intent = new Intent(KaryawanCallCenterActivity.this, MenuKaryawanActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(KaryawanCallCenterActivity.this, HistoryKaryawanActivity.class);
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
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("loading data..");
//        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_CALL_CENTER,
                new Response.Listener<String>() {
                    public void onResponse(String response) {

                        try {
                            Log.v("response", String.valueOf(response));
                            JSONObject aduan = new JSONObject(response);
                            JSONArray aduanarr = aduan.getJSONArray("array_call_center");
                            for(int i =0; i<aduanarr.length(); i++){
                                JSONObject obj = aduanarr.getJSONObject(i);
                                String katagori = obj.getString("Katagori");
                                String subkatagori = obj.getString("SubKatagori");
                                String nomortelp = obj.getString("Nomor");
                                CallModel nomor = new CallModel(katagori, subkatagori, nomortelp);
                                listnomor.add(nomor);
                            }
                            AdapterCall = new AdapterCall(listnomor,  KaryawanCallCenterActivity.this);
                            rev.setAdapter(AdapterCall);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KaryawanCallCenterActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String,String> getParams(){
                HashMap<String, String> params = new HashMap<>();
                SharedPreferences prefs = getSharedPreferences("siskasharedpref", MODE_PRIVATE);
                String npk = prefs.getString("keyenpk", null);
                params.put("NPKPelapor", npk);
                return  params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
