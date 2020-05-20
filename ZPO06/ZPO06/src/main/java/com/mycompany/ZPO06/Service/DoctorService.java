/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ZPO06.Service;

import com.mycompany.ZPO06.Model.Doctor;
import com.mycompany.ZPO06.Repository.DoctorRepo;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;
    
    /*@PostConstruct
    private void init(){
        doctorRepo.save(new Doctor(0l,"Borys","Testowy",33,"Pediatra"));
        doctorRepo.save(new Doctor(0l,"Cezary","Test",43,"Pediatra"));
        doctorRepo.save(new Doctor(0l,"Daniel","Nowy",53,"Ortopeda"));
        log.info("added doctors");
    }*/
}
