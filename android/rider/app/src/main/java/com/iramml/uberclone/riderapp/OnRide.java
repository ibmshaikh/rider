package com.iramml.uberclone.riderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iramml.uberclone.riderapp.activity.HomeActivity;

public class OnRide extends AppCompatActivity {

    private String startTime,Address,riderName;
    private TextView txtstartTime,txtAddress,txtriderName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_ride);

        txtstartTime = findViewById(R.id.startTime);
        txtAddress = findViewById(R.id.address);
        txtriderName = findViewById(R.id.ridername);


        if (getIntent() != null) {
            Address = getIntent().getStringExtra("Address");
            startTime = getIntent().getStringExtra("startTime");
            riderName = getIntent().getStringExtra("Rider Name");

            txtAddress.setText(Address);
            txtriderName.setText(riderName);
            txtstartTime.setText(startTime);

        }

        DatabaseReference mref = FirebaseDatabase.getInstance().getReference();
        mref.child("OnRide").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(OnRide.this,"Your Ride has been completed",Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}