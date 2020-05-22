package com.example.Hotel.reposytory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Hotel.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
