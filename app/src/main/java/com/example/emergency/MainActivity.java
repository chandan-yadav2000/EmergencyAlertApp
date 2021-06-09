package com.example.emergency;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isConnected(MainActivity.this)) {
            builderDialog(MainActivity.this).show();
        } else {
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(MainActivity.this, PhoneActivity.class);
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();
        }
    }

    public boolean isConnected(Context c) {

        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {

            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return mobile != null && networkInfo.isConnectedOrConnecting() ||
                    (wifi != null && networkInfo.isConnectedOrConnecting());

        } else return false;

    }


    public AlertDialog.Builder builderDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Error");
        builder.setMessage("Internet not available, Cross check your internet connectivity and try again later...");
        builder.setIcon(R.drawable.alerticon);

        builder.setPositiveButton("OK", (dialog, which) -> finish());
        return builder;
    }

}