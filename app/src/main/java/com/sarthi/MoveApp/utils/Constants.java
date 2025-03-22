package com.sarthi.MoveApp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    
    // Firebase Collection Names
    public static final String USERS_COLLECTION = "users";
    public static final String BOOKINGS_COLLECTION = "bookings";
    
    // Intent Extra Keys
    public static final String BOOKING_ID = "booking_id";
    public static final String USER_ID = "user_id";
    
    // Common strings used across app
    public static final String APP_NAME = "Sarthi";
    
    // List of Indian cities for the app
    public static List<String> getIndianCities() {
        return new ArrayList<>(Arrays.asList(
                "Mumbai", "Delhi", "Bangalore", "Hyderabad", "Chennai", 
                "Kolkata", "Pune", "Ahmedabad", "Jaipur", "Lucknow",
                "Kanpur", "Nagpur", "Indore", "Thane", "Bhopal",
                "Visakhapatnam", "Patna", "Vadodara", "Ghaziabad", "Ludhiana",
                "Agra", "Nashik", "Faridabad", "Meerut", "Rajkot",
                "Varanasi", "Srinagar", "Aurangabad", "Dhanbad", "Amritsar"
        ));
    }
    
    // List of vehicle types
    public static List<String> getVehicleTypes() {
        return new ArrayList<>(Arrays.asList(
                "Mini Truck", "Pickup Truck", "3-Wheeler", "Large Truck",
                "Commercial Van", "Trailer", "Refrigerated Truck"
        ));
    }
}