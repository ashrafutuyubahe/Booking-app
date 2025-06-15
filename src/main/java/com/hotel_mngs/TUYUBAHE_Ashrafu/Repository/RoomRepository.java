package com.hotel_mngs.TUYUBAHE_Ashrafu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel_mngs.TUYUBAHE_Ashrafu.Models.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Long hotelId);
    List<Room> findByHotelIdAndIsAvailableTrue(Long hotelId);

}
