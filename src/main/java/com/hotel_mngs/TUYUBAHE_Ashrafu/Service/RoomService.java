package com.hotel_mngs.TUYUBAHE_Ashrafu.Service;

import java.util.List;
import java.util.Optional;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Room;

public interface RoomService {
    Room saveRoom(Room room);
    List<Room> getAllRooms();
    Optional<Room> getRoomById(Long id);
    Room updateRoom(Long id, Room room);
    void deleteRoom(Long id);
    List<Room> getRoomsByHotelId(Long hotelId);
    List<Room> getAvailableRooms(Long hotelId); 
}
