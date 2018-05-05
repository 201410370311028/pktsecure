package com.example.rangg.pktsecure.Petugas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rangg.pktsecure.Api.Api;
import com.example.rangg.pktsecure.Api.RequestHandler;
import com.example.rangg.pktsecure.Api.SharedPrefManager;
import com.example.rangg.pktsecure.Model.PenggunaModel;
import com.example.rangg.pktsecure.Petugas.Action.korban;
import com.example.rangg.pktsecure.Petugas.Action.pelaku;
import com.example.rangg.pktsecure.Petugas.Action.petugas_action;
import com.example.rangg.pktsecure.Petugas.tab.verifikasi;
import com.example.rangg.pktsecure.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by rangg on 25/03/2018.
 */

public class PelengkapanDataActivity extends AppCompatActivity{

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;

    public TextView Id, Status;
    public Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pelengkapan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("PKT SECURE");

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
//                    case 0:
//                        intent = new Intent(PelengkapanDataActivity.this, PetugasHomeActivity.class);
//                        startActivity(intent);
//                        break;
//                    case 1:
//                        intent = new Intent(PelengkapanDataActivity.this, PetugasHistoryActivity.class);
//                        startActivity(intent);
//                        break;
//                    case 2:
//                        intent = new Intent(PelengkapanDataActivity.this, PetugasMenuActivity.class);
//                        startActivity(intent);
//                        break;
//                    case 3:
//                        intent = new Intent(PelengkapanDataActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        break;
                    default:
                        Log.d("Test", "Default");
                        break;
                }
            }
        });

        Id = (TextView)findViewById(R.id.Id);
        Status = (TextView) findViewById(R.id.Status);

        buttonNext = (Button)findViewById(R.id.done);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    update_status();
                    Intent intent = new Intent(PelengkapanDataActivity.this, verifikasi.class);
                    startActivity(intent);
            }
        });

        updateTextLabel();

    }

    private void updateTextLabel() {
        Bundle extras = getIntent().getExtras();

        String IDI= extras.getString("ID");
        Id.setText(IDI);
    }


    private void update_status() {

        String id = Id.getText().toString().trim();



        HashMap<String, String> params = new HashMap<>();
        params.put("ID", id);

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_STATUS, params, CODE_POST_REQUEST);
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


    public void petugas(View view) {
        Intent petugas = new Intent(PelengkapanDataActivity.this, petugas_action.class);
        startActivity(petugas);
    }

    public void korban(View view) {
        Intent petugas = new Intent(PelengkapanDataActivity.this, korban.class);
        startActivity(petugas);
    }

    public void pelaku(View view) {
        Intent petugas = new Intent(PelengkapanDataActivity.this, pelaku.class);
        startActivity(petugas);
    }
    public void done(View view) {

        Intent petugas = new Intent(PelengkapanDataActivity.this, verifikasi.class);
        startActivity(petugas);
    }
}
