package com.example.emergency;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class ProfileActivity extends AppCompatActivity {
    TextInputEditText Name, Phone1, Phone2, Message;
    Button Show;
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Hooks
        Name = findViewById(R.id.fullName);
        Phone1 = findViewById(R.id.person1);
        Phone2 = findViewById(R.id.person2);
        Message = findViewById(R.id.message);
        Show=findViewById(R.id.saveButton);


        //Firebase to retrive data from firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users");
        DatabaseReference uidRef = rootRef.child(uid);

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String uid = ds.getKey();
                    String A = snapshot.child(uid).child("fullName").getValue(String.class);
                    String B = snapshot.child(uid).child("phone1").getValue(String.class);
                    String C = snapshot.child(uid).child("phone2").getValue(String.class);
                    String D = snapshot.child(uid).child("message").getValue(String.class);
                    Name.setText(A);
                    Name.setEnabled(false);
                    Phone1.setText(B);
                    Phone1.setEnabled(false);
                    Phone2.setText(C);
                    Phone2.setEnabled(false);
                    Message.setText(D);
                    Message.setEnabled(false);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}