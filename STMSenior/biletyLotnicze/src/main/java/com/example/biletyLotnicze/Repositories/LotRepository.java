package com.example.biletyLotnicze.Repositories;

import com.example.biletyLotnicze.Models.Lot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface LotRepository extends CrudRepository<Lot, Long> {
    public List<Lot> findByData(LocalDate data);

}
