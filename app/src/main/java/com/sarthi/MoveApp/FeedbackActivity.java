package com.sarthi.MoveApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FeedbackActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RatingBar ratingBar;
    private EditText commentEditText;
    private Button submitButton;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Set up toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Provide Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        ratingBar = findViewById(R.id.rating_bar);
        commentEditText = findViewById(R.id.comment_edit_text);
        submitButton = findViewById(R.id.submit_button);
        progressBar = findViewById(R.id.progress_bar);

        // Set up submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        float rating = ratingBar.getRating();
        String comment = commentEditText.getText().toString().trim();

        // Validate inputs
        if (rating == 0) {
            Toast.makeText(this, "Please provide a rating", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(comment)) {
            commentEditText.setError("Please provide your feedback");
            commentEditText.requestFocus();
            return;
        }

        // Show progress
        progressBar.setVisibility(View.VISIBLE);

        // Create feedback map
        String feedbackId = UUID.randomUUID().toString();
        String userId = mAuth.getCurrentUser().getUid();
        long timestamp = System.currentTimeMillis();

        Map<String, Object> feedbackMap = new HashMap<>();
        feedbackMap.put("feedbackId", feedbackId);
        feedbackMap.put("userId", userId);
        feedbackMap.put("rating", rating);
        feedbackMap.put("comment", comment);
        feedbackMap.put("timestamp", timestamp);

        // Save feedback to Firebase
        mDatabase.child("feedback").child(feedbackId).setValue(feedbackMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(FeedbackActivity.this, "Thank you for your feedback!", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(FeedbackActivity.this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
