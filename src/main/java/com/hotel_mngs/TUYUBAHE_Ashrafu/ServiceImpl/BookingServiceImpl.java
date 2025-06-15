package com.hotel_mngs.TUYUBAHE_Ashrafu.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Dto.BookingRequest;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Billing;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Booking;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Room;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.User;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Repository.BillingRepository;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Repository.BookingRepository;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Repository.RoomRepository;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Repository.UserRepository;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Service.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired private BookingRepository bookingRepo;
    @Autowired private RoomRepository roomRepo;
    @Autowired private BillingRepository billingRepo;
    @Autowired private UserRepository userRepo;

    @Override
    public Booking bookRoom(BookingRequest request, String customerEmail) {
        Room room = roomRepo.findById(request.getRoomId()).orElseThrow();
        if (!room.isAvailable()) throw new RuntimeException("Room not available");

        User customer = userRepo.findByUserEmail(customerEmail).orElseThrow();
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setHotel(room.getHotel());
        booking.setCustomer(customer);
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setStatus("CONFIRMED");

        room.setAvailable(false);
        roomRepo.save(room);
        Booking saved = bookingRepo.save(booking);

        Billing billing = new Billing();
        long days = ChronoUnit.DAYS.between(request.getCheckIn(), request.getCheckOut());
        billing.setBooking(saved);
        billing.setAmount(room.getPrice() * days);
        billing.setGeneratedAt(LocalDateTime.now());
        billingRepo.save(billing);

        return saved;
    }

    @Override
    public List<Booking> getBookingsByCustomer(String email) {
        User customer = userRepo.findByUserEmail(email).orElseThrow();
        return bookingRepo.findByCustomer(customer);
    }

    @Override
    public void cancelBooking(Long bookingId, String email) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow();
        if (!booking.getCustomer().getUserEmail().equals(email)) throw new RuntimeException("Unauthorized");
        if (!booking.getStatus().equals("CONFIRMED")) throw new RuntimeException("Booking cannot be cancelled");
        booking.setStatus("CANCELLED");
        booking.getRoom().setAvailable(true);
        roomRepo.save(booking.getRoom());
        bookingRepo.save(booking);
    }

@Override
public Billing getBillingForBooking(Long bookingId) {
    Booking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
    return billingRepo.findByBookingId(booking).orElseThrow(() -> new RuntimeException("Billing not found for this booking"));
}

}
