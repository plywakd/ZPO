package com.example.biletyLotnicze.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq")
    @GenericGenerator(name = "seq", strategy="increment")
    private Long id;
    private String name;
    private String surname;

    public Klient(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @OneToMany
    List<Bilet> bilet;
}
