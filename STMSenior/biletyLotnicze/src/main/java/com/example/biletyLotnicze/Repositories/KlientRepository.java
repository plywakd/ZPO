package com.example.biletyLotnicze.Repositories;

import com.example.biletyLotnicze.Models.Klient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KlientRepository extends CrudRepository<Klient, Long> {
    public Optional<Klient> findByNameAndSurname(String name, String surname);
}
