package com.example.rangg.pktsecure;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rangg on 24/03/2018.
 */

public class CameraActivity  extends AppCompatActivity{
    ImageView ivCamera, textureView, ivUpload, ivGallery;

    private final int CAMERA_RESULT = 101;
    private String picturePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachment);

        ivCamera = (ImageView)findViewById(R.id.camera);
        textureView =(ImageView) findViewById(R.id.imageview);
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    dispatchTakenPictureIntent();Toast.makeText(getApplicationContext(),"permission needed", Toast.LENGTH_LONG).show();
                } else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){

                        }
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_RESULT);
                    }
                }
                Log.v("gambar e", "asdasdasdadsdas");
            }
        });

        ivGallery = (ImageView)findViewById(R.id.gallery);
        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void dispatchTakenPictureIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, CAMERA_RESULT);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_OK) {
            if (requestCode == CAMERA_RESULT) {

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File image = null;
                try {
                    image = File.createTempFile(
                            imageFileName,  /* prefix */
                            ".jpg",         /* suffix */
                            storageDir      /* directory */
                    );

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Save a file: path for use with ACTION_VIEW intents
                picturePath = image.getAbsolutePath();
                Log.v("gambar e", String.valueOf(image));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_RESULT){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakenPictureIntent();
            }
            else {
                Toast.makeText(getApplicationContext(),"permission needed", Toast.LENGTH_LONG).show();
            }
        }

        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
