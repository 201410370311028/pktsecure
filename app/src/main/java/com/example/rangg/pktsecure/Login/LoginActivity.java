package com.example.rangg.pktsecure.Login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rangg.pktsecure.Api.RequestHandler;
import com.example.rangg.pktsecure.Api.SharedPrefManager;
import com.example.rangg.pktsecure.Api.Api;
import com.example.rangg.pktsecure.Karyawan.Pengaduan.MenuKaryawanActivity;
import com.example.rangg.pktsecure.Model.PenggunaModel;
import com.example.rangg.pktsecure.Petugas.tab.on_proses;
import com.example.rangg.pktsecure.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.Username);
        editTextPassword = (EditText) findViewById(R.id.Password);


        //if user presses on login
        //calling the method login
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        PenggunaModel user = new PenggunaModel(
                                userJson.getString("NPK"),
                                userJson.getString("Username"),
                                userJson.getString("Hubungan"),
                                userJson.getString("Alamat"),
                                userJson.getString("Telepon"),
                                userJson.getString("IsAktif"),
                                userJson.getString("Role")
                        );

                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        finish();
                        if (userJson.getString("IsAktif").equals("1")) {
                            if (userJson.getString("Role").equals("1")) {
                                Toast.makeText(getApplicationContext(), "Aktif role 1", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(), PelaporanPetugasActivity.class));
                            }

                            if (userJson.getString("Role").equals("2")) {
                                Toast.makeText(getApplicationContext(), "Aktif role 2", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), on_proses.class));
                            }

                            if (userJson.getString("Role").equals("3")) {
                                Toast.makeText(getApplicationContext(), "Aktif role 3", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(), PelaporanPetugasActivity.class));
                            }

                            if (userJson.getString("Role").equals("4")) {
                                Toast.makeText(getApplicationContext(), "Aktif role 4", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(), PelaporanPetugasActivity.class));
                            }

                            if (userJson.getString("Role").equals("5")) {
                                Toast.makeText(getApplicationContext(), "Aktif role 5", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MenuKaryawanActivity.class));
                            }

                            if (userJson.getString("Role").equals("6")) {
                                Toast.makeText(getApplicationContext(), "Aktif role 6", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(), MenuKaryawanActivity.class));
                            }

                            if (userJson.getString("Role").equals("7")) {
                                Toast.makeText(getApplicationContext(), "Aktif role 7", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(), MenuKaryawanActivity.class));
                            }
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Akun Anda Telah Non Aktif", Toast.LENGTH_LONG).show();
                        }
                    }

                    else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("Username", username);
                params.put("Password", password);

                //returing the response
                return requestHandler.sendPostRequest(Api.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }


}