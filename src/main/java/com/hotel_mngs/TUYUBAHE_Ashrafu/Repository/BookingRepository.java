package com.hotel_mngs.TUYUBAHE_Ashrafu.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Booking;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.User;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Room;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find all bookings by customer
    List<Booking> findByCustomer(User user);

    // Optional: Find bookings by customer ID
    List<Booking> findByCustomerUserId(User customer);

    // Find all bookings by room
    List<Booking> findByRoom(Room room);

    // Find bookings by room ID
    List<Booking> findByRoomId(Long roomId);

    // Optional: Find booking by ID and customer (used for ownership validation)
    Optional<Booking> findByIdAndCustomer(Long bookingId, User customer);

    // Optional: Get all bookings with status (e.g., CONFIRMED, CANCELLED, etc.)
    List<Booking> findByStatus(String status);


}

