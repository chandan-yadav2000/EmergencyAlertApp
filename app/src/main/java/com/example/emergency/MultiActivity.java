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
    CardView police;
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
        helpline helpline1 = new helpline();


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
                String Call = "100";
                if (Call.trim().length() > 0) {
                    if (ContextCompat.checkSelfPermission(MultiActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MultiActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE}, 1);
                    } else {
                        String dial = "tel:"+ "100";
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse(dial));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                        startActivity(callIntent);
                    }
                } else {
                    Toast.makeText(MultiActivity.this, "No Records", Toast.LENGTH_SHORT).show();
                }
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