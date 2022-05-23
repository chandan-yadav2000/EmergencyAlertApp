package com.example.emergency.helpline;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.emergency.MultiActivity;

public class helpline extends MultiActivity {

    public Context mContext;

    public helpline(Context context) {
        this.mContext = context;
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
        }
    }

    public void FireFighter(){
        String Call = "101";
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

    public void Ambulance(){
        String Call = "102";
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

    public void WomenHelpline(){
        String Call = "1091";
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

    public void roadAccident(){
        String Call = "1073";
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

    public void railwayHelpline(){
        String Call = "1072";
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

    public void seniorCitizen(){
        String Call = "14567";
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

    public void farmer(){
        String Call = "1551";
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

    public void childLabour(){
        String Call = "1098";
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

    public void LpgGasSystem(){
        String Call = "1906";
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

    public void cyberSecurity(){
        String Call = "155620";
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

    public void animalSafetyCall(){
        String Call = "9820122602";
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
