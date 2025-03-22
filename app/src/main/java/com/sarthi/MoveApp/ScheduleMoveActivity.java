package com.sarthi.MoveApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sarthi.MoveApp.models.Booking;
import com.sarthi.MoveApp.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class ScheduleMoveActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner pickupCitySpinner, dropCitySpinner, vehicleTypeSpinner;
    private EditText pickupAddressEditText, dropAddressEditText, itemsDetailsEditText, additionalNotesEditText;
    private TextView dateTextView, timeTextView;
    private Button selectDateButton, selectTimeButton, scheduleBookingButton;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Calendar selectedDateTime;
    private String[] indianCities;
    private String[] vehicleTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_move);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        selectedDateTime = Calendar.getInstance();

        // Set up toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Schedule a Move");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        pickupCitySpinner = findViewById(R.id.pickup_city_spinner);
        dropCitySpinner = findViewById(R.id.drop_city_spinner);
        vehicleTypeSpinner = findViewById(R.id.vehicle_type_spinner);
        pickupAddressEditText = findViewById(R.id.pickup_address_edit_text);
        dropAddressEditText = findViewById(R.id.drop_address_edit_text);
        itemsDetailsEditText = findViewById(R.id.items_details_edit_text);
        additionalNotesEditText = findViewById(R.id.additional_notes_edit_text);
        dateTextView = findViewById(R.id.date_text_view);
        timeTextView = findViewById(R.id.time_text_view);
        selectDateButton = findViewById(R.id.select_date_button);
        selectTimeButton = findViewById(R.id.select_time_button);
        scheduleBookingButton = findViewById(R.id.schedule_booking_button);
        progressBar = findViewById(R.id.progress_bar);

        // Get arrays from constants
        indianCities = Constants.INDIAN_CITIES;
        vehicleTypes = Constants.VEHICLE_TYPES;

        // Setup spinners
        setupSpinners();

        // Setup date and time buttons
        setupDateTimeButtons();

        // Setup schedule booking button
        scheduleBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleBooking();
            }
        });

        // Set a future date (7 days ahead) as default for scheduling
        selectedDateTime.add(Calendar.DAY_OF_MONTH, 7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateTextView.setText(dateFormat.format(selectedDateTime.getTime()));

        // Set 10:00 AM as default time
        selectedDateTime.set(Calendar.HOUR_OF_DAY, 10);
        selectedDateTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        timeTextView.setText(timeFormat.format(selectedDateTime.getTime()));
    }

    private void setupSpinners() {
        // Setup city spinners
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, indianCities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pickupCitySpinner.setAdapter(cityAdapter);
        dropCitySpinner.setAdapter(cityAdapter);

        // Setup vehicle type spinner
        ArrayAdapter<String> vehicleAdapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, vehicleTypes);
        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleTypeSpinner.setAdapter(vehicleAdapter);
    }

    private void setupDateTimeButtons() {
        // Setup date selection
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ScheduleMoveActivity.this, 
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                selectedDateTime.set(Calendar.YEAR, year);
                                selectedDateTime.set(Calendar.MONTH, monthOfYear);
                                selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                                dateTextView.setText(dateFormat.format(selectedDateTime.getTime()));
                            }
                        }, 
                        selectedDateTime.get(Calendar.YEAR), 
                        selectedDateTime.get(Calendar.MONTH), 
                        selectedDateTime.get(Calendar.DAY_OF_MONTH));
                
                // Set minimum date as tomorrow
                Calendar minDate = Calendar.getInstance();
                minDate.add(Calendar.DAY_OF_MONTH, 1);
                datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        // Setup time selection
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ScheduleMoveActivity.this, 
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                selectedDateTime.set(Calendar.MINUTE, minute);
                                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.US);
                                timeTextView.setText(timeFormat.format(selectedDateTime.getTime()));
                            }
                        }, 
                        selectedDateTime.get(Calendar.HOUR_OF_DAY), 
                        selectedDateTime.get(Calendar.MINUTE), 
                        false);
                timePickerDialog.show();
            }
        });
    }

    private void scheduleBooking() {
        // Get input values
        String pickupCity = pickupCitySpinner.getSelectedItem().toString();
        String dropCity = dropCitySpinner.getSelectedItem().toString();
        String vehicleType = vehicleTypeSpinner.getSelectedItem().toString();
        String pickupAddress = pickupAddressEditText.getText().toString().trim();
        String dropAddress = dropAddressEditText.getText().toString().trim();
        String itemsDetails = itemsDetailsEditText.getText().toString().trim();
        String additionalNotes = additionalNotesEditText.getText().toString().trim();
        String date = dateTextView.getText().toString();
        String time = timeTextView.getText().toString();

        // Validate inputs
        if (TextUtils.isEmpty(pickupAddress)) {
            pickupAddressEditText.setError("Pickup address is required");
            pickupAddressEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(dropAddress)) {
            dropAddressEditText.setError("Drop address is required");
            dropAddressEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(itemsDetails)) {
            itemsDetailsEditText.setError("Items details are required");
            itemsDetailsEditText.requestFocus();
            return;
        }

        // Show progress
        progressBar.setVisibility(View.VISIBLE);

        // Create booking object
        String bookingId = UUID.randomUUID().toString();
        String userId = mAuth.getCurrentUser().getUid();
        String status = "Scheduled";
        long timestamp = selectedDateTime.getTimeInMillis();
        
        Booking booking = new Booking(
                bookingId, 
                userId, 
                pickupCity, 
                pickupAddress, 
                dropCity, 
                dropAddress,
                date,
                time,
                vehicleType,
                itemsDetails,
                additionalNotes,
                status,
                timestamp
        );

        // Save booking to Firebase
        mDatabase.child("bookings").child(bookingId).setValue(booking)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(ScheduleMoveActivity.this, "Move scheduled successfully", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(ScheduleMoveActivity.this, "Failed to schedule move", Toast.LENGTH_SHORT).show();
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
