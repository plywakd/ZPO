package com.example.biletyLotnicze.Repositories;

import com.example.biletyLotnicze.Models.Bilet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiletRepository extends CrudRepository<Bilet, Long> {
    public List<Bilet> findByLotId(Long id);

}
