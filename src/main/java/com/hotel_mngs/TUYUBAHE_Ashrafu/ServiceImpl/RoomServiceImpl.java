package com.hotel_mngs.TUYUBAHE_Ashrafu.ServiceImpl;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Room;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Repository.RoomRepository;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        return roomRepository.findById(id)
                .map(room -> {
                     room.setRoomType(updatedRoom.getRoomType());
                    room.setPrice(updatedRoom.getPrice());
                    room.setAvailable(updatedRoom.isAvailable());
                    room.setHotel(updatedRoom.getHotel());
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Room> getAvailableRooms(Long hotelId) {
        return roomRepository.findByHotelIdAndIsAvailableTrue(hotelId);
    }
}
