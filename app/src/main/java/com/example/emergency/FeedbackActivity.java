package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    TextInputEditText Name,FeedbackMessage;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    Button SendFeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Name= findViewById(R.id.name);
        FeedbackMessage= findViewById(R.id.feedback);
        mAuth=FirebaseAuth.getInstance();
        SendFeedback = findViewById(R.id.sendFeedback);

        SendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                databaseReference = rootNode.getReference("Feedback");

                String name123 = Name.getText().toString();
                String feedbackMessage = FeedbackMessage.getText().toString();

                FeedbackHelperClass feedbackHelperClass = new FeedbackHelperClass(name123,feedbackMessage);
                databaseReference.child(name123).setValue(feedbackHelperClass);

                Toast.makeText(FeedbackActivity.this, "Your FeedBack has been Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}