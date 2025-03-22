package com.sarthi.MoveApp.models;

import java.util.Date;

public class Booking {
    private String bookingId;
    private String userId;
    private String pickupLocation;
    private String dropoffLocation;
    private String vehicleType;
    private String itemsDescription;
    private String additionalNotes;
    private Date bookingDate;
    private String status; // "pending", "confirmed", "in_progress", "completed", "cancelled"
    
    // Empty constructor required for Firestore
    public Booking() {
    }
    
    public Booking(String userId, String pickupLocation, String dropoffLocation, 
                 String vehicleType, String itemsDescription, String additionalNotes) {
        this.userId = userId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.vehicleType = vehicleType;
        this.itemsDescription = itemsDescription;
        this.additionalNotes = additionalNotes;
        this.bookingDate = new Date();
        this.status = "pending";
    }
    
    // Getters and setters
    public String getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getPickupLocation() {
        return pickupLocation;
    }
    
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    
    public String getDropoffLocation() {
        return dropoffLocation;
    }
    
    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public String getItemsDescription() {
        return itemsDescription;
    }
    
    public void setItemsDescription(String itemsDescription) {
        this.itemsDescription = itemsDescription;
    }
    
    public String getAdditionalNotes() {
        return additionalNotes;
    }
    
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
    
    public Date getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}