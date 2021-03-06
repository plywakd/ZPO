/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ZPO06v2.Controller;

import com.mycompany.ZPO06v2.Repository.PatientRepo;
import com.mycompany.ZPO06v2.Model.Patient;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepo patientRepo;
    
    @GetMapping("/all")
    public List<Patient> getAll(){
        return (List<Patient>) patientRepo.findAll();
    }
    
    public Patient getByPesel(String pesel){
        return patientRepo.findByPesel(pesel);
    }
    
    @GetMapping("/addPatient")
    public void addPatient(Patient patient){
        patientRepo.save(patient);
    }
    
    @GetMapping("/delPatient")
    public void delPatient(Patient patient){
        patientRepo.delete(patient);
    }
}
