package com.example.emergency;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneActivity extends AppCompatActivity {
    private EditText editTextMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        editTextMobile = findViewById(R.id.editTextMobile);

        findViewById(R.id.buttonContinue).setOnClickListener(v -> {
            String mobile = editTextMobile.getText().toString().trim();
            if(mobile.isEmpty() || mobile.length() < 10){
                editTextMobile.setError("Enter a valid mobile");
                editTextMobile.requestFocus();
                return;
            }

            Intent intent = new Intent(PhoneActivity.this,OtpActivity.class);
            intent.putExtra("PhoneNumber", mobile);
            startActivity(intent);
        });
    }
}