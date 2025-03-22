package com.sarthi.MoveApp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarthi.MoveApp.BookingDetailsActivity;
import com.sarthi.MoveApp.R;
import com.sarthi.MoveApp.models.Booking;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Adapter for displaying booking items in a RecyclerView
 */
public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    
    private Context context;
    private List<Booking> bookingList;
    private SimpleDateFormat dateFormat;
    
    public BookingAdapter(Context context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
    }
    
    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        
        holder.tvPickupLocation.setText(booking.getPickupLocation());
        holder.tvDropoffLocation.setText(booking.getDropoffLocation());
        holder.tvStatus.setText(booking.getStatus());
        
        if (booking.getBookingDate() != null) {
            holder.tvDate.setText(dateFormat.format(booking.getBookingDate()));
        } else {
            holder.tvDate.setText("N/A");
        }
        
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookingDetailsActivity.class);
            intent.putExtra("bookingId", booking.getBookingId());
            context.startActivity(intent);
        });
    }
    
    @Override
    public int getItemCount() {
        return bookingList != null ? bookingList.size() : 0;
    }
    
    public void updateData(List<Booking> newBookingList) {
        this.bookingList = newBookingList;
        notifyDataSetChanged();
    }
    
    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView tvPickupLocation, tvDropoffLocation, tvStatus, tvDate;
        
        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPickupLocation = itemView.findViewById(R.id.tvPickupLocation);
            tvDropoffLocation = itemView.findViewById(R.id.tvDropoffLocation);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}