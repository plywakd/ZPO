
package com.demo2.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private LocalDate visitDate;
    
//    public Visit(LocalDate visitDate){
//        this.visitDate=visitDate;
//    }
    
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;
    
}
