package com.hotel_mngs.TUYUBAHE_Ashrafu.Service;



import java.util.List;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.BookingRequest;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Billing;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Booking;


public interface BookingService {
    Booking bookRoom(BookingRequest request, String customerEmail);
    List<Booking> getBookingsByCustomer(String email);
    void cancelBooking(Long bookingId, String email);
    Billing getBillingForBooking(Long bookingId);
}
