package com.example.emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {

    TextInputLayout Name,Message,Phone1,Phone2;
    Button updateButton;
    DatabaseReference rootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //Hooks used........
        Name = findViewById(R.id.fullName);
        Message = findViewById(R.id.message);
        Phone1 = findViewById(R.id.person1);
        Phone2 = findViewById(R.id.person2);
        updateButton = findViewById(R.id.updateButton);

        rootRef = FirebaseDatabase.getInstance().getReference().child("Users");

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String uid = ds.getKey();
                    String A = snapshot.child(uid).child("fullName").getValue(String.class);
                    String B = snapshot.child(uid).child("phone1").getValue(String.class);
                    String C = snapshot.child(uid).child("phone2").getValue(String.class);
                    String D = snapshot.child(uid).child("message").getValue(String.class);
                    Name.getEditText().setText(A);
                    Phone1.getEditText().setText(B);
                    Phone2.getEditText().setText(C);
                    Message.getEditText().setText(D);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updateDetails()){
                    try {
                        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()) {
                                    String uid = ds.getKey();
                                    String A = snapshot.child(uid).child("fullName").getValue(String.class);
                                    String B = snapshot.child(uid).child("phone1").getValue(String.class);
                                    String C = snapshot.child(uid).child("phone2").getValue(String.class);
                                    String D = snapshot.child(uid).child("message").getValue(String.class);
                                    Name.getEditText().setText(A);
                                    Phone1.getEditText().setText(B);
                                    Phone2.getEditText().setText(C);
                                    Message.getEditText().setText(D);
                                }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(UpdateActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                   catch (Exception ex){
                        Toast.makeText(UpdateActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                   }
                    finally {
                        Toast.makeText(UpdateActivity.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private boolean updateDetails() {
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String uid = ds.getKey();
                    try {
                        rootRef.child(uid).child("fullName").setValue(Name);
                        rootRef.child(uid).child("phone1").setValue(Phone1);
                        rootRef.child(uid).child("phone2").setValue(Phone2);
                        rootRef.child(uid).child("message").setValue(Message);
                        Toast.makeText(UpdateActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception ex){
                        Toast.makeText(UpdateActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        Intent intent = new Intent(UpdateActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }

}