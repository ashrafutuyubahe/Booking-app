package com.hotel_mngs.TUYUBAHE_Ashrafu.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Hotel;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Service.HotelService;

@RestController
@RequestMapping("/api")
public class HotelController {

    @Autowired private HotelService hotelService;

    @PostMapping("/hotels")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.addHotel(hotel));
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotel")
    public ResponseEntity<Hotel> getHotelById(@RequestParam Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
}