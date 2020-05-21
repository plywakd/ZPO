/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ZPO06v2.Service;

import com.mycompany.ZPO06v2.Repository.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;
    
    /*@PostConstruct
    private void init(){
        patientRepo.save(new Patient(0l,"Alfa","Testy",22,"98071304000"));
        patientRepo.save(new Patient(0l,"Beta","Test",23,"97071303000"));

    }*/
    
}
