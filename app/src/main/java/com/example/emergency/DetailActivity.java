package com.example.emergency;

import static android.graphics.Color.*;

import static com.example.emergency.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DetailActivity extends AppCompatActivity {

    TextInputLayout _FullName,_Phone1,_Phone2,_Message,_Num;
    Button saveButton;
    ProgressBar _ProgressBar;

    //Firebase Reference......
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_detail);

        //Hooks.....
        _FullName = findViewById(id.fullName);
        _Num = findViewById(id.number);
        _Phone1 = findViewById(id.phone1);
        _Phone2 = findViewById(id.phone2);
        _Message = findViewById(id.message);
        saveButton = findViewById(id.saveButton);
        _ProgressBar = findViewById(id.progressBar);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckAllNumbers() && ValidateField()) {
                    saveData();
                }
            }
        });
    }



    //Validating all field are filled or not......
    private boolean ValidateField(){

        if (_FullName.getEditText().getText().toString().length() == 0) {
            _FullName.setError("Please provide your FullName");
            _FullName.setErrorTextColor(ColorStateList.valueOf(Color.WHITE));
            _FullName.setEnabled(true);
            return false;
        }
        else if (_Phone1.getEditText().getText().toString().isEmpty()) {
            _Phone1.setError("Please enter the number call in emergency");
            _Phone1.setErrorTextColor(ColorStateList.valueOf(Color.WHITE));
            _Phone1.setEnabled(true);
            return false;
        }
        else if (_Phone2.getEditText().getText().toString().isEmpty()) {
            _Phone2.setError("Please enter the number of person");
            _Phone2.setErrorTextColor(ColorStateList.valueOf(Color.WHITE));
            _Phone2.setEnabled(true);
            return false;
        }
        else if (_Message.getEditText().getText().toString().isEmpty()) {
            _Message.setError("Please Input your Message");
            _Message.setErrorTextColor(ColorStateList.valueOf(Color.WHITE));
            _Message.setEnabled(true);
            return false;
        }
        else if (_Num.getEditText().getText().toString().isEmpty()) {
            _Num.setError("Please provide your phone Number");
            _Num.setErrorTextColor(ColorStateList.valueOf(Color.WHITE));
            _Num.setEnabled(true);
            return false;
        }
        return true;
    }

    //Popup Message if Phone Number Matches in case......
    private boolean CheckAllNumbers(){

        if (_Phone1.getEditText().getText().toString().equals(_Phone2.getEditText().getText().toString()) &&
                _Phone2.getEditText().getText().toString().equals(_Num.getEditText().getText().toString()) &&
                _Num.getEditText().getText().toString().equals(_Phone1.getEditText().getText().toString())) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);
            alertDialog.setTitle("Info");
            alertDialog.setMessage("The Number that you Provided are same");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setPositiveButton("OK", null);
            alertDialog.show();
        }
        else if (_Phone1.getEditText().getText().toString().equals(_Phone2.getEditText().getText().toString())) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);
            alertDialog.setTitle("Info");
            alertDialog.setMessage("The Number that you Provided for Contact Person 1 and Person 2 is same");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setPositiveButton("OK", null);
            alertDialog.show();

        }
        else if (_Phone2.getEditText().getText().toString().equals(_Num.getEditText().getText().toString()) ) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);
            alertDialog.setTitle("Info");
            alertDialog.setMessage("The Number that you Provided for Contact Person 2 and your number is same");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setPositiveButton("OK", null);
            alertDialog.show();

        }
        else if (_Num.getEditText().getText().toString().equals(_Phone1.getEditText().getText().toString()) ) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);
            alertDialog.setTitle("Info");
            alertDialog.setMessage("The Number that you Provided for Contact Person 1 and your number is same");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setPositiveButton("OK", null);
            alertDialog.show();

        }
        return true;
    }

    //Saving user data in Firebase Database.....
    private void saveData() {
            saveButton.setVisibility(View.GONE);
            _ProgressBar.setVisibility(View.VISIBLE);
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference().child("Users");

            String fullName = _FullName.getEditText().getText().toString();
            String phone1 = _Phone1.getEditText().getText().toString();
            String phone2 = _Phone2.getEditText().getText().toString();
            String message = _Message.getEditText().getText().toString();
            String number = _Num.getEditText().getText().toString();

            //final String userId = FirebaseDatabase.getInstance().getReference().push().getKey(); //Create new random Id


            UserHelperClass helperClass = new UserHelperClass(fullName, phone1, phone2, message, number);
            reference.child(number).setValue(helperClass);

            //Otp Activity
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + number,
                    60,
                    TimeUnit.SECONDS,
                    DetailActivity.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            Toast.makeText(DetailActivity.this, "All Data have been Saved!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(DetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            Intent intent = new Intent(DetailActivity.this, OtpActivity.class);
                            intent.putExtra("mobile", _Num.getEditText().getText().toString());
                            intent.putExtra("verificationId", verificationId);
                            startActivity(intent);
                        }
                    }
            );
        }
}



