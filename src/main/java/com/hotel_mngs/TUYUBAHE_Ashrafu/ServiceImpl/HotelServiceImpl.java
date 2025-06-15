package com.hotel_mngs.TUYUBAHE_Ashrafu.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Hotel;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Repository.HotelRepository;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired private HotelRepository hotelRepo;

    @Override
    public Hotel addHotel(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepo.findById(id).orElseThrow();
    }
}