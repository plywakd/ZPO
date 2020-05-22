package com.example.Hotel.reposytory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Hotel.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
