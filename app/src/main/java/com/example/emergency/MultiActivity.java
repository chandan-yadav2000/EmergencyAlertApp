package com.example.emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.emergency.helpline.helpline;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MultiActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    CardView police,fireFighter,ambulance,womenHelpline,roadAccident,railwayEnquiry,
    seniorCitizen,farmer,childLabour,LPG,cyberSecurity,animalSafety;
    Context context;
    private static final int REQUEST_CALL = 1;
    private static final String CALL_PRIVILEGED = "android.permission.CALL_PRIVILEGED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        //Hooks
        drawerLayout = findViewById(R.id.drawerLayout1);
        navigationView = findViewById(R.id.navigation_view1);
        police = findViewById(R.id.policeClick);
        fireFighter = findViewById(R.id.fireClick);
        ambulance = findViewById(R.id.ambulanceClick);
        womenHelpline = findViewById(R.id.womenClick);
        roadAccident = findViewById(R.id.roadAccidentClick);
        railwayEnquiry = findViewById(R.id.railwayClick);
        seniorCitizen = findViewById(R.id.seniorClick);
        farmer = findViewById(R.id.farmerClick);
        childLabour = findViewById(R.id.childLabourClick);
        LPG = findViewById(R.id.lpgGas);
        cyberSecurity = findViewById(R.id.cyberClick);
        animalSafety = findViewById(R.id.animalClick);
        helpline helpline1 = new helpline(MultiActivity.this);


        //Navigation View
        findViewById(R.id.imageMenu12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.policeCall();
            }
        });

        fireFighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.FireFighter();
            }
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.Ambulance();
            }
        });

        womenHelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.WomenHelpline();
            }
        });

        roadAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.roadAccident();
            }
        });

        railwayEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.railwayHelpline();
            }
        });

        seniorCitizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.seniorCitizen();
            }
        });

        farmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.farmer();
            }
        });

        childLabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.childLabour();
            }
        });

        LPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.LpgGasSystem();
            }
        });

        cyberSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.cyberSecurity();
            }
        });

        animalSafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpline1.animalSafetyCall();
            }
        });


    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int i = item.getItemId();

        if(i == R.id.nav_home){
            Intent intent = new Intent(MultiActivity.this,HomeActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_profile){
            Intent intent = new Intent(MultiActivity.this,ProfileActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_edit){
            Intent intent = new Intent(MultiActivity.this,UpdateActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_nearby){
            Intent intent = new Intent(MultiActivity.this,NearbyActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_helpline){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        else if(i == R.id.nav_feedback){
            Intent intent = new Intent(MultiActivity.this,FeedbackActivity.class);
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

        Intent intent = new Intent(MultiActivity.this,FirstActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}