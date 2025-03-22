package com.sarthi.MoveApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sarthi_app.R;

public class HomeActivity extends AppCompatActivity {

    private Button btnProfile, btnMyBookings, btnBookMove, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnProfile = findViewById(R.id.btnProfile);
        btnMyBookings = findViewById(R.id.btnMyBookings);
        btnBookMove = findViewById(R.id.btnBookMove);
        btnLogout = findViewById(R.id.btnLogout);

        btnProfile.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, ProfileActivity.class)));
        btnMyBookings.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, MyBookingsActivity.class)));
        btnBookMove.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, BookMoveActivity.class)));
        btnLogout.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
