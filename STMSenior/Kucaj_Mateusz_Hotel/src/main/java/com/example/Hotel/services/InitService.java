package com.example.Hotel.services;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Hotel.models.Booking;
import com.example.Hotel.models.Client;
import com.example.Hotel.models.Room;
import com.example.Hotel.reposytory.BookingRepository;
import com.example.Hotel.reposytory.ClientRepository;
import com.example.Hotel.reposytory.RoomRepository;

@Service
public class InitService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	@PostConstruct
	public void init() {
		
		Room r1 = new Room(0l,300.0 ,true,2);
		Room r2 = new Room(0l,500.0,false,4);
		Room r3 = new Room(0l, 100.0,true,1);
		Room r4 = new Room(0l, 1300.0,false,4);
		Room r5= new Room(0l, 1000.0,false,3);
		Room r6= new Room(0l, 1300.0,true,3);
		Room r7= new Room(0l, 1600.0,true,4);
		Room r8= new Room(0l, 300.0,false,2);
		Client c1 = new Client(0l,"Tomek","Kowalski");
		Client c2 = new Client(0l,"Andrzej","Nowak");
		
		
		r1 =roomRepository.save(r1);
		r2 =roomRepository.save(r2);
		r3 =roomRepository.save(r3);
		r4 =roomRepository.save(r4);
		r5 =roomRepository.save(r5);
		r6 =roomRepository.save(r6);
		r7=roomRepository.save(r7);
		r8=roomRepository.save(r8);
		
		c1 =clientRepository.save(c1);
		c2 =clientRepository.save(c2);		
		
		LocalDate start1;

		start1= LocalDate.now();
		LocalDate end1;
		end1 = LocalDate.now().plusDays(5);
				
				
		Booking b1 = new Booking(0l,start1,end1,false,c1,r1);
		Booking b2 = new Booking(0l,start1,end1,false,c1,r3);
		
		b1=bookingRepository.save(b1);
		b2=bookingRepository.save(b2);	
		
	}
	
	
}
