package com.hotel_mngs.TUYUBAHE_Ashrafu.Service;

import java.util.List;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Hotel;

public interface HotelService {
    Hotel addHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotelById(Long id);
}