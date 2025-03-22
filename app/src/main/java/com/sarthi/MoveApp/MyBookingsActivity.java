package com.sarthi.MoveApp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sarthi_app.R;

import java.util.List;

public class MyBookingsActivity extends AppCompatActivity {

    private ListView lvBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        lvBookings = findViewById(R.id.lvBookings);

        // Mock booking data
        List<String> bookingsList = List.of(
                "Booking 1: Pune to Mumbai - 25 Mar 2025",
                "Booking 2: Nagpur to Pune - 26 Mar 2025"
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookingsList);
        lvBookings.setAdapter(adapter);
    }
}
