package com.example.emergency.helpline;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.emergency.HomeActivity;
import com.example.emergency.MultiActivity;

public class helpline extends Activity {

    public Context mContext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void policeCall(){
        String Call = "100";

        if (Call.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(mContext.getApplicationContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mContext.getApplicationContext(),
                        new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                String dial = "tel:" + Call;
                mContext.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(mContext.getApplicationContext(), "No Records", Toast.LENGTH_SHORT).show();
        }
    }

}
