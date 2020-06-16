package com.example.biletyLotnicze.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class RaportService {
    @Autowired
    BiletRepository biletRepository;

    @Autowired
    KlientRepository klientRepository;

    @Autowired
    LotRepository lotRepository;

    
}
