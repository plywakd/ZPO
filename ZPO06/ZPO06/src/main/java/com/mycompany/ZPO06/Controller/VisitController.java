/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ZPO06.Controller;

import com.mycompany.ZPO06.Model.Patient;
import com.mycompany.ZPO06.Repository.VisitRepo;
import com.mycompany.ZPO06.Model.Visit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visits")
public class VisitController {
    @Autowired
    private VisitRepo visitRepo;
    
    @GetMapping("/all")
    public List<Visit> getAll(){
        return (List<Visit>) visitRepo.findAll();
    }
    
    @GetMapping("/addVisit")
    public void addVisit(Visit visit){
        visitRepo.save(visit);
    }
    @GetMapping("/delVisit")
    public void delVisit(Visit visit){
        visitRepo.delete(visit);
    }
}
