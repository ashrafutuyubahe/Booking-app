package com.hotel_mngs.TUYUBAHE_Ashrafu.Models;


import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import com.hotel_mngs.TUYUBAHE_Ashrafu.Classes.ResetToken;

import jakarta.persistence.Column;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



// Room.java
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Hotel hotel;

    private String roomType;
    private double price;
    private boolean isAvailable;
   
}