package com.hotel_mngs.TUYUBAHE_Ashrafu.Controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.BookingRequest;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Billing;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Booking;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Booking> bookRoom(@RequestBody BookingRequest request, String customerEmail) {
        return ResponseEntity.ok(bookingService.bookRoom(request,customerEmail));
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<Booking> getMyBookings(String customerEmail) {
        return bookingService.getBookingsByCustomer(customerEmail);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id,String customerEmail) {
        bookingService.cancelBooking(id,customerEmail);
        return ResponseEntity.ok("Booking canceled");
    }

    @GetMapping("/billing/{bookingId}")
    public ResponseEntity<Billing> getBilling(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBillingForBooking(bookingId));
    }
}
