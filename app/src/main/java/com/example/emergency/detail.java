package com.example.emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.jar.Attributes;

public class detail extends AppCompatActivity {

    private EditText Name,P1,P2,Message;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Name = findViewById(R.id.Name);
        P1= findViewById(R.id.phone);
        P2= findViewById(R.id.phone2);
        Message= findViewById(R.id.message);
        submit= findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("Name",Name.getText().toString());
                map.put("Phone 1",P1.getText().toString());
                map.put("Phone 2",P2.getText().toString());
                map.put("Message",Message.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("Users").push()
                .setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("hello","Your data has been uploaded");
                        Toast.makeText(detail.this,"Your data has been uploaded",Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent  = new Intent(detail.this,MainButton.class);
                startActivity(intent);
            }
        });


    }
}