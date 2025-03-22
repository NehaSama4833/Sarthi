package com.sarthi.MoveApp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sarthi_app.R;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvName, tvEmail, tvPhone;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        btnBack = findViewById(R.id.btnBack);

        // Mock data for profile
        tvName.setText("Name: John Doe");
        tvEmail.setText("Email: johndoe@example.com");
        tvPhone.setText("Phone: +91 9876543210");

        btnBack.setOnClickListener(v -> finish());
    }
}
