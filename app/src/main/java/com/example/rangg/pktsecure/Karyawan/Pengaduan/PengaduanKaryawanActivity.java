package com.example.rangg.pktsecure.Karyawan.Pengaduan;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.example.rangg.pktsecure.Model.KategoriAduanModel;
import com.example.rangg.pktsecure.Model.PenggunaModel;
import com.example.rangg.pktsecure.CameraActivity;
import com.example.rangg.pktsecure.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


/**
 * Created by rangg on 01/03/2018.
 */

public class PengaduanKaryawanActivity extends AppCompatActivity {

    private List<KategoriAduanModel> listKategori = new ArrayList<>();

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private Calendar dateTime = Calendar.getInstance();

    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;

    EditText editTextNamaPelapor, editTextNPKPelapor, editTextJudul, editTextLokasi, editTextNoTelepon, editTextTindakan;
    TextView textViewNoPengaduan, textviewWaktu;
    Spinner spinnerKategoriAduan;

    Button buttonTanggal, buttonWaktu, buttonSubmit;

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);

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
                        intent = new Intent(PengaduanKaryawanActivity.this, MenuKaryawanActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(PengaduanKaryawanActivity.this, HistoryKaryawanActivity.class);
                        startActivity(intent);
                        break;

                    default:

                        Log.d("Test","Default");
                        break;
                }
            }
        });

        editTextNamaPelapor = (EditText) findViewById(R.id.NamaPelapor);
        editTextNPKPelapor = (EditText) findViewById(R.id.NPKPelapor);
        textViewNoPengaduan = (TextView) findViewById(R.id.NoPengaduan);
        editTextJudul = (EditText) findViewById(R.id.Judul);
        editTextLokasi = (EditText) findViewById(R.id.Lokasi);
        editTextNoTelepon = (EditText) findViewById(R.id.NoTelepon);
        editTextTindakan = (EditText) findViewById(R.id.Tindakan);
        textviewWaktu = (TextView) findViewById(R.id.Waktu);

        spinnerKategoriAduan = (Spinner) findViewById(R.id.spinner_kategori);

        spinnerKategoriAduan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonTanggal = (Button) findViewById(R.id.button_tanggal);
        buttonWaktu = (Button) findViewById(R.id.button_waktu);
        buttonSubmit = (Button) findViewById(R.id.button_submit);


        buttonTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        buttonWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdating) {
                    //updatepengaduan();
                } else {
                    createpengaduan();
                    prepareKategoriAduanSpinner();
                    Intent intent = new Intent(PengaduanKaryawanActivity.this, CameraActivity.class);
                    startActivity(intent);
                }
            }
        });

        updateTextLabel();

    }

    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.DAY_OF_MONTH), dateTime.get(Calendar.MONTH)).show();
    }

    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();
        }

    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextLabel();
        }
    };

    private void updateTextLabel() {

        textviewWaktu.setText(sdf.format(dateTime.getTime()));

        PenggunaModel user = SharedPrefManager.getInstance(this).getUser();
        editTextNoTelepon.setText(user.getTelepon());
        editTextNPKPelapor.setText(user.getNPK());
        editTextNamaPelapor.setText(user.getUsername());
        editTextLokasi.setText(user.getAlamat());
        textViewNoPengaduan.setText("LAP");

    }

    private void prepareKategoriAduanSpinner(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.URL_VL_KATEGORI_ADUAN,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            Log.v("12223", String.valueOf(response));
                            JSONObject aduan = new JSONObject(response);
                            JSONArray aduanarr = aduan.getJSONArray("array_kategoriaduan");
                            for(int i =0; i<aduanarr.length(); i++){
                                JSONObject obj = aduanarr.getJSONObject(i);
                                String Kategori = obj.getString("Kategori");
                                String IDKategori = obj.getString("ID");

                                KategoriAduanModel kategoriAduanModel = new KategoriAduanModel(Kategori, IDKategori);
                                listKategori.add(kategoriAduanModel);
                            }
                            KategoriAduanSpinnerAdapter kategoriAduanSpinnerAdapter = new KategoriAduanSpinnerAdapter(PengaduanKaryawanActivity.this, listKategori);
                            spinnerKategoriAduan.setAdapter(kategoriAduanSpinnerAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PengaduanKaryawanActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void createpengaduan() {

        String npk = editTextNPKPelapor.getText().toString().trim();
        String pelapor = editTextNamaPelapor.getText().toString().trim();
        String judul = editTextJudul.getText().toString().trim();
        String waktu = textviewWaktu.getText().toString().trim();
        String lokasi = editTextLokasi.getText().toString().trim();
        String telepon = editTextNoTelepon.getText().toString().trim();
        String tindak = editTextTindakan.getText().toString().trim();
        String kategori = spinnerKategoriAduan.getSelectedItem().toString();
        String noPengaduan = textViewNoPengaduan.getText().toString().trim();
        Log.v(  "errorony disini", String.valueOf(spinnerKategoriAduan.getSelectedItem()));
        if (TextUtils.isEmpty(npk)) {
            editTextJudul.setError("masukan NPK");
            editTextJudul.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pelapor)) {
            editTextNamaPelapor.setError("masukan Pelapor");
            editTextNamaPelapor.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(judul)) {
            editTextJudul.setError("masukan judul pengaduan");
            editTextJudul.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(waktu)) {
            textviewWaktu.setError("masukan waktu kejadian");
            textviewWaktu.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(lokasi)) {
            editTextLokasi.setError("masukan lokasi kejadian");
            editTextLokasi.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(telepon)) {
            editTextNoTelepon.setError("masukan no telepon anda");
            editTextNoTelepon.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(tindak)) {
            editTextTindakan.setError("masukan tindakan pertama");
            editTextTindakan.requestFocus();
            return;
        }


        HashMap<String, String> params = new HashMap<>();
        params.put("NPKPelapor", npk);
        params.put("NamaPelapor", pelapor);
        params.put("Judul", judul);
        params.put("Waktu", waktu);
        params.put("Lokasi", lokasi);
        params.put("NoTelepon", telepon);
        params.put("Tindakan", tindak);
        params.put("KategoriAduan", kategori);
        params.put("NoPengaduan", noPengaduan);

        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_CREATE_PENGADUAN, params, CODE_POST_REQUEST);
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
