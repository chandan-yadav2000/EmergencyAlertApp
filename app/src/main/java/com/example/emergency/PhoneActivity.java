package com.example.emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;

import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.concurrent.TimeUnit;


public class PhoneActivity extends AppCompatActivity {

    //Hooks
    Button send;
    TextInputEditText number;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallBacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        //Adjust the screen when keyboard get opens.........
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        progressBar= findViewById(R.id.progressBar);
        send= findViewById(R.id.sendButton);
        number= findViewById(R.id.Number);

        mAuth = FirebaseAuth.getInstance();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number.getText().toString().isEmpty()) {
                    number.setError("Please provide your Phone Number");
                    number.requestFocus();
                    number.setEnabled(true);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    send.setVisibility(View.INVISIBLE);

                    //Otp Activity
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + number.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            PhoneActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    signInWithPhoneAuthCredential(phoneAuthCredential);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    send.setVisibility(View.VISIBLE);
                                    Toast.makeText(PhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.GONE);
                                    send.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(PhoneActivity.this, OtpActivity.class);
                                    intent.putExtra("mobile", number.getText().toString());
                                    intent.putExtra("verificationId", verificationId);
                                    startActivity(intent);
                                }
                            }

                    );
                }
            }
        });


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            send.setVisibility(View.VISIBLE);
                            Toast.makeText(PhoneActivity.this, "Login Successful...", Toast.LENGTH_SHORT).show();
                            Intent phoneLoginIntent  =new Intent (getApplicationContext(),HomeActivity.class);
                            phoneLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(phoneLoginIntent);
                            finish();
                        } else {
                            String message = task.getException().toString();
                            Toast.makeText(PhoneActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}