package com.hotel_mngs.TUYUBAHE_Ashrafu.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Room;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Service.RoomService;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired private RoomService roomService;

    @PostMapping("/rooms")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.saveRoom(room));
    }

    @GetMapping("/hotels/{hotelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public List<Room> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }
}
