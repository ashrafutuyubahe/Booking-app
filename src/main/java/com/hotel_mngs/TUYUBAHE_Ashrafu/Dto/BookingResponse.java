package com.hotel_mngs.TUYUBAHE_Ashrafu.Dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingResponse {
    private Long bookingId;
    private UserDTO customer;
    private HotelDTO hotel;
    private RoomDTO room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;
}

