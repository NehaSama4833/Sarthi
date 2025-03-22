package com.sarthi.MoveApp.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {
    
    // Firebase Authentication
    private static final FirebaseAuth auth = FirebaseAuth.getInstance();
    
    // Firestore Database
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    
    // Check if user is logged in
    public static boolean isLoggedIn() {
        return auth.getCurrentUser() != null;
    }
    
    // Get current user
    public static FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }
    
    // Get current user ID
    public static String getCurrentUserId() {
        return isLoggedIn() ? getCurrentUser().getUid() : null;
    }
    
    // Get users collection reference
    public static CollectionReference getUsersCollection() {
        return db.collection(Constants.USERS_COLLECTION);
    }
    
    // Get current user's document reference
    public static DocumentReference getCurrentUserDocument() {
        return isLoggedIn() ? getUsersCollection().document(getCurrentUserId()) : null;
    }
    
    // Get bookings collection reference
    public static CollectionReference getBookingsCollection() {
        return db.collection(Constants.BOOKINGS_COLLECTION);
    }
    
    // Get user's bookings collection reference
    public static CollectionReference getUserBookingsCollection() {
        return isLoggedIn() ? 
                getBookingsCollection().document(getCurrentUserId()).collection("user_bookings") : 
                null;
    }
    
    // Sign out current user
    public static void signOut() {
        auth.signOut();
    }
}