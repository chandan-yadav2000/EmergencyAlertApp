package com.example.emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int REQUEST_CALL = 1;
    NavigationView navigationView;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    ImageButton alertButton;
    DrawerLayout drawerLayout;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView locality, longitude, latitude, address, country;
    GPSTracker gpsTracker;
    public String A , B, C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Hooks
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        mAuth = FirebaseAuth.getInstance();
        alertButton = findViewById(R.id.alert);

        //Temporary
        locality = findViewById(R.id.Locality);
        longitude = findViewById(R.id.Longitude);
        latitude = findViewById(R.id.Latitude);
        address = findViewById(R.id.Address);
        country = findViewById(R.id.Country);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(HomeActivity.this);

        gpsTracker = new GPSTracker(HomeActivity.this);
        if (getLocationPStatus()){
            gpsTracker.getLocation();
        }

        retrieveData();

        //Main Button
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        sendSms();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }

                getLocationPStatus();
                makePhoneCall();

            }
        });

        //Navigation View
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

    }

    //retrieve user data
    public void retrieveData() {
        //Firebase to retrieve data from firebase
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users");

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String uid = ds.getKey();
                   A = snapshot.child(uid).child("phone1").getValue(String.class);
                   B = snapshot.child(uid).child("phone2").getValue(String.class);
                   C = snapshot.child(uid).child("message").getValue(String.class);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makePhoneCall() {
        String Call = "+91" + A;
        if (Call.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(HomeActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + Call;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(HomeActivity.this, "No Records", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendSms() {
        try {
            String[] sPhone = {A,B};
            String dataMessage = C;
            String message = dataMessage + "http://maps.google.com/maps?saddr=" + gpsTracker.getLatitude() + "," + gpsTracker.getLongitude() + " " + "I am  here";
            SmsManager smsManager = SmsManager.getDefault();
            for (String mess : sPhone)
                smsManager.sendTextMessage(mess, null, message, null, null);
            Toast.makeText(HomeActivity.this, "Message send successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(HomeActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        }

    private boolean getLocationPStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                return true;
            else{
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                return false;
            }
        }else
            return true;
    }


    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Exist");
        builder.setMessage("Are you sure you want to exist ?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("OK", (dialog, which) -> {
            HomeActivity.super.onBackPressed();
            finish();
        }).setNegativeButton("Cancel",null).setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int i = item.getItemId();

        if(i == R.id.nav_home){
            drawerLayout.closeDrawer(GravityCompat.START);
          }
        else if(i == R.id.nav_profile){
            Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_edit){
            Intent intent = new Intent(HomeActivity.this,UpdateActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_helpline){
            Intent intent = new Intent(HomeActivity.this,MultiActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
       else if(i == R.id.nav_nearby){
        Intent intent = new Intent(HomeActivity.this,NearbyActivity.class);
        startActivity(intent);
       }
        else if(i == R.id.nav_feedback){
            Intent intent = new Intent(HomeActivity.this,FeedbackActivity.class);
            startActivity(intent);
        }
        else if(i == R.id.nav_Logout){
         mAuth.signOut();
         signOutUser();
        }
        return true;
    }

    //Sign Out
    private void signOutUser() {

        Intent intent = new Intent(HomeActivity.this,FirstActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
