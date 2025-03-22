package com.sarthi.MoveApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sarthi.MoveApp.models.Booking;

public class BookingDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView bookingIdTextView;
    private TextView statusTextView;
    private TextView dateTimeTextView;
    private TextView pickupCityTextView;
    private TextView pickupAddressTextView;
    private TextView dropCityTextView;
    private TextView dropAddressTextView;
    private TextView vehicleTypeTextView;
    private TextView itemsDetailsTextView;
    private TextView additionalNotesTextView;
    private Button feedbackButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        // Get booking ID from intent
        bookingId = getIntent().getStringExtra("BOOKING_ID");
        if (bookingId == null) {
            Toast.makeText(this, "Booking details not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Set up toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Booking Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        bookingIdTextView = findViewById(R.id.booking_id_text_view);
        statusTextView = findViewById(R.id.status_text_view);
        dateTimeTextView = findViewById(R.id.date_time_text_view);
        pickupCityTextView = findViewById(R.id.pickup_city_text_view);
        pickupAddressTextView = findViewById(R.id.pickup_address_text_view);
        dropCityTextView = findViewById(R.id.drop_city_text_view);
        dropAddressTextView = findViewById(R.id.drop_address_text_view);
        vehicleTypeTextView = findViewById(R.id.vehicle_type_text_view);
        itemsDetailsTextView = findViewById(R.id.items_details_text_view);
        additionalNotesTextView = findViewById(R.id.additional_notes_text_view);
        feedbackButton = findViewById(R.id.feedback_button);

        // Load booking details
        loadBookingDetails();

        // Set up feedback button
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingDetailsActivity.this, FeedbackActivity.class));
            }
        });
    }

    private void loadBookingDetails() {
        mDatabase.child("bookings").child(bookingId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Booking booking = dataSnapshot.getValue(Booking.class);
                    displayBookingDetails(booking);
                } else {
                    Toast.makeText(BookingDetailsActivity.this, "Booking not found", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BookingDetailsActivity.this, "Failed to load booking details", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void displayBookingDetails(Booking booking) {
        bookingIdTextView.setText("Booking ID: " + booking.getBookingId());
        statusTextView.setText("Status: " + booking.getStatus());
        dateTimeTextView.setText("Date & Time: " + booking.getDate() + " " + booking.getTime());
        pickupCityTextView.setText("Pickup City: " + booking.getPickupCity());
        pickupAddressTextView.setText("Pickup Address: " + booking.getPickupAddress());
        dropCityTextView.setText("Drop City: " + booking.getDropCity());
        dropAddressTextView.setText("Drop Address: " + booking.getDropAddress());
        vehicleTypeTextView.setText("Vehicle Type: " + booking.getVehicleType());
        itemsDetailsTextView.setText("Items Details: " + booking.getItemsDetails());
        
        if (booking.getAdditionalNotes() != null && !booking.getAdditionalNotes().isEmpty()) {
            additionalNotesTextView.setText("Additional Notes: " + booking.getAdditionalNotes());
        } else {
            additionalNotesTextView.setText("Additional Notes: None");
        }

        // Only show feedback button for completed bookings
        if (booking.getStatus().equals("Completed")) {
            feedbackButton.setVisibility(View.VISIBLE);
        } else {
            feedbackButton.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
