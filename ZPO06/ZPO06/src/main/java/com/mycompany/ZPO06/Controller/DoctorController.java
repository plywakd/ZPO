/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ZPO06.Controller;

import com.mycompany.ZPO06.Repository.DoctorRepo;
import com.mycompany.ZPO06.Model.Doctor;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    
    private final DoctorRepo doctorRepo;
    
    public DoctorController(DoctorRepo doctorRepo){
        this.doctorRepo=doctorRepo;
    }
    
    @GetMapping("/all")
    public List<Doctor> getAll(){
        return (List<Doctor>) doctorRepo.findAll();
    }
    
    @GetMapping("/{specialization}")
    public List<Doctor> getBySpecialization(@PathVariable("specialization")String specialization){
        return doctorRepo.findBySpecialization(specialization);
    }
    
    @GetMapping("/addDoctor")
    public void addDoctor(Doctor doctor){
        doctorRepo.save(doctor);
    }
    @GetMapping("/delDoctor")
    public void delDoctor(Doctor doctor){
        doctorRepo.delete(doctor);
    }
}
