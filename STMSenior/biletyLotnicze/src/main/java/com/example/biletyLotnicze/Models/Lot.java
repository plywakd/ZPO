package com.example.biletyLotnicze.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq")
    @GenericGenerator(name = "seq", strategy="increment")
    private Long id;
    private int maxMiejsc;
    private LocalDate data;

    public Lot(int maxMiejsc, LocalDate data) {
        this.maxMiejsc = maxMiejsc;
        this.data = data;
    }

    @OneToMany
    List<Bilet> bilet;
}
