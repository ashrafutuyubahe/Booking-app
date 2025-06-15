package com.hotel_mngs.TUYUBAHE_Ashrafu.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User customer;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status; // PENDING, CONFIRMED, CANCELLED
}