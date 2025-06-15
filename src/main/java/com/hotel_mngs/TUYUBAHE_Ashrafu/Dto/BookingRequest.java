package com.hotel_mngs.TUYUBAHE_Ashrafu.Dto;



import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long customerId;
    private Long hotelId;
    private Long roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
}

