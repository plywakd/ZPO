
package com.demo2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String surname;
    private Integer pesel;
    private Boolean hospitalized;

    public Patient(String surname, Integer pesel, Boolean hospitalized) {
        this.surname = surname;
        this.pesel = pesel;
        this.hospitalized = hospitalized;
    }
    
    
    
}
