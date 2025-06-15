package com.hotel_mngs.TUYUBAHE_Ashrafu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Billing;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Booking;

import java.util.List;
import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing, Long> {

   
    List<Billing> findByGuestId(Long guestId);


    List<Billing> findByHotelId(Long hotelId);


    List<Billing> findByRoomId(Long roomId);

    
   Optional<Billing> findByBookingId(Booking booking);
      Optional<Billing> findByBooking(Booking booking);
}
