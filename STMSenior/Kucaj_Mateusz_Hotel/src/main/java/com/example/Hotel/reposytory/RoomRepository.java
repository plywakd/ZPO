package com.example.Hotel.reposytory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Hotel.models.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

}
