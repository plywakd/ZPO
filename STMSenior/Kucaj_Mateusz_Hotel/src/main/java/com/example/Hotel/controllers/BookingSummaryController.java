package com.example.Hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hotel.models.Booking;
import com.example.Hotel.reposytory.BookingRepository;

@RestController
@RequestMapping("/api/hotel")
public class BookingSummaryController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@GetMapping("/summary")
	public List<Booking> getSummary() {
		return bookingRepository.findAll();
	}
}
