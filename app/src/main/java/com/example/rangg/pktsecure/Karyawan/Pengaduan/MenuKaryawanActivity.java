package com.example.rangg.pktsecure.Karyawan.Pengaduan;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.rangg.pktsecure.Api.SharedPrefManager;
import com.example.rangg.pktsecure.GPS.GPSTracker;
import com.example.rangg.pktsecure.Karyawan.CallCenter.KaryawanCallCenterActivity;
import com.example.rangg.pktsecure.Karyawan.Panduan.KaryawanPanduanActivity;
import com.example.rangg.pktsecure.Model.PenggunaModel;
import com.example.rangg.pktsecure.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by rangg on 01/03/2018.
 */

public class MenuKaryawanActivity extends AppCompatActivity{

    FirebaseDatabase database;
    DatabaseReference myRef;
    CardView btnShowLocation;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    GPSTracker gps;
    TextView location;

    Dialog myDialog;

    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        myDialog = new Dialog(this);

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
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("History Pengaduan").withIcon(getResources().getDrawable(R.drawable.history)));
        navigationDrawerLeft.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MenuKaryawanActivity.this, MenuKaryawanActivity.class);
                        startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(MenuKaryawanActivity.this, HistoryKaryawanActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        Log.d("Test","Default");
                        break;
                }
            }
        });

        try{
            if(ActivityCompat.checkSelfPermission(this, mPermission)!= MockPackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new  String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        btnShowLocation = (CardView)findViewById(R.id.button_panic);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(MenuKaryawanActivity.this);
                location = (TextView)findViewById(R.id.locationText);
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longtitude = gps.getLongtitude();
                    database = FirebaseDatabase.getInstance();
                    location.setText(latitude+","+longtitude);
                    myRef = database.getReference("Location");
                    myRef.setValue(latitude+","+longtitude);
                }else{
                    gps.showSettingAlert();
                }
            }
        });
    }

    public void button_pengaduan(View view) {
        Intent adukan = new Intent(MenuKaryawanActivity.this, PengaduanKaryawanActivity.class);
        startActivity(adukan);
    }


    public void panic(View view) {
    }

    public void button_call(View view){
        Intent call = new Intent(MenuKaryawanActivity.this, KaryawanCallCenterActivity.class);
        startActivity(call);
    }

    public void button_panduan(View view) {
        Intent panduan = new Intent(MenuKaryawanActivity.this, KaryawanPanduanActivity.class);
        startActivity(panduan);
    }

}
