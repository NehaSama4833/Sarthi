package com.sarthi.MoveApp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sarthi_app.R;

public class BookMoveActivity extends AppCompatActivity {

    private EditText etPickup, etDropoff, etDate, etTime;
    private Spinner spinnerVehicleType, spinnerHelpers;
    private Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_move);

        etPickup = findViewById(R.id.etPickup);
        etDropoff = findViewById(R.id.etDropoff);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        spinnerVehicleType = findViewById(R.id.spinnerVehicleType);
        spinnerHelpers = findViewById(R.id.spinnerHelpers);
        btnBook = findViewById(R.id.btnBookMove);

        ArrayAdapter<CharSequence> vehicleAdapter = ArrayAdapter.createFromResource(this,
                R.array.vehicle_types, android.R.layout.simple_spinner_item);
        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicleType.setAdapter(vehicleAdapter);

        ArrayAdapter<CharSequence> helpersAdapter = ArrayAdapter.createFromResource(this,
                R.array.helpers_count, android.R.layout.simple_spinner_item);
        helpersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHelpers.setAdapter(helpersAdapter);

        btnBook.setOnClickListener(v -> {
            if (etPickup.getText().toString().isEmpty() ||
                    etDropoff.getText().toString().isEmpty() ||
                    etDate.getText().toString().isEmpty() ||
                    etTime.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
