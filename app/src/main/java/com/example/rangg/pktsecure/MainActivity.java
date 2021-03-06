package com.example.rangg.pktsecure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    String value = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                TextView textView = (TextView)findViewById(R.id.LocText);
                textView.setText(value);
                String [] separated = value.split(",");
                String latiPos = separated[0].trim();
                String longiPos = separated[1].trim();

                double dLat = Double.parseDouble(latiPos);
                double dLong = Double.parseDouble(longiPos);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    protected void buttonClicked(View view){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("LOCVAL", value);
        startActivity(intent);
    }
}
