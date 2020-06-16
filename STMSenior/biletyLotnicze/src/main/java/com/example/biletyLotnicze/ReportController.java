package com.example.biletyLotnicze;

import com.example.biletyLotnicze.Models.Bilet;
import com.example.biletyLotnicze.Models.Klient;
import com.example.biletyLotnicze.Repositories.BiletRepository;
import com.example.biletyLotnicze.Repositories.KlientRepository;
import com.example.biletyLotnicze.Repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ReportController {
    @Autowired
    BiletRepository biletRepository;
    @Autowired
    KlientRepository klientRepository;
    @Autowired
    LotRepository lotRepository;

    @RequestMapping("/api/bilet")
    public Iterable biletReport(){
        return biletRepository.findAll();
    }

    @RequestMapping("/api/klient")
    public Collection<Bilet> klientReport(){
        Collection<Bilet> col = (Collection<Bilet>) biletRepository.findAll();
        return col;
    }

    public Klient returnKlient(Klient klient){
        return klient;
    }

    public Iterable<Bilet> returnBilet(Iterable<Bilet> bilet){
        return bilet;
    }

    @RequestMapping("/api/lot")
    public Iterable lotReport(){
        return lotRepository.findAll();
    }

}
