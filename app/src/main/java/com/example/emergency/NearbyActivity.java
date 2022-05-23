package com.example.emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.emergency.map.NearbyMap;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class NearbyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    CardView policeStation,hospital,medicalStore,
            hotelLodges,restaurant,ATM,airPort,railwayStation,busStation,
            petrolPump,autoMotive,animalHospital;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        //Hooks
        drawerLayout = findViewById(R.id.drawerLayout1);
        navigationView = findViewById(R.id.navigation_view1);
        policeStation = findViewById(R.id.policeStationClick);
        hospital = findViewById(R.id.hospitalClick);
        medicalStore = findViewById(R.id.medicalClick);
        hotelLodges = findViewById(R.id.hotelClick);
        restaurant = findViewById(R.id.restaurantClick);
        ATM = findViewById(R.id.atmClick);
        airPort = findViewById(R.id.airportClick);
        railwayStation = findViewById(R.id.railStationClick);
        busStation = findViewById(R.id.busStation);
        petrolPump = findViewById(R.id.petrolPump);
        autoMotive = findViewById(R.id.autoMotiveClick);
        animalHospital = findViewById(R.id.animalHospitalClick);


        //Class Hook
        NearbyMap nearbyMap = new NearbyMap(NearbyActivity.this);

        //Navigation View
        findViewById(R.id.imageMenu12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        policeStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapPoliceStation();
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapHospital();
            }
        });

        medicalStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapMedicalStore();
            }
        });

        hotelLodges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapHotel();
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapRestaurant();
            }
        });

        ATM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapAtm();
            }
        });

        airPort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapAirport();
            }
        });

        railwayStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapRailwayStation();
            }
        });

        busStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapBusStation();
            }
        });

        petrolPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapPetrolPump();
            }
        });

        autoMotive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapAutomotive();

            }
        });

        animalHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyMap.mapAnimalHospital();
            }
        });

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int i = item.getItemId();

        if(i == R.id.nav_home){
            Intent intent = new Intent(NearbyActivity.this,HomeActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_profile){
            Intent intent = new Intent(NearbyActivity.this,ProfileActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_edit){
            Intent intent = new Intent(NearbyActivity.this,UpdateActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_helpline){
            Intent intent = new Intent(NearbyActivity.this,MultiActivity.class);
            //intent.putExtra("sendNumber",getIntent().getStringExtra("PhoneNumber"));
            startActivity(intent);
        }
        else if(i == R.id.nav_nearby){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(i == R.id.nav_feedback){
            Intent intent = new Intent(NearbyActivity.this,FeedbackActivity.class);
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

        Intent intent = new Intent(NearbyActivity.this,FirstActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}