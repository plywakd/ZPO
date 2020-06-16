package com.example.biletyLotnicze.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Bilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq")
    @GenericGenerator(name = "seq", strategy="increment")
    private Long id;
    private int cena;
    private int miejsce;
    private boolean przyOknie;

    public Bilet(int cena, int miejsce, boolean przyOknie) {
        this.cena = cena;
        this.miejsce = miejsce;
        this.przyOknie = przyOknie;
    }

    @Nullable
    @ManyToOne
    private Klient klient;
    @ManyToOne
    private Lot lot;
}
