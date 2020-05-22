
package com.demo2.controllers;

import com.demo2.models.Visit;
import com.demo2.repositories.VisitRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospital")
public class VisitController {
    
    @Autowired
    private VisitRepository visitRepo;
    
    @GetMapping("/summary")
    public List<Visit> getSummary(){
        return visitRepo.findAll();
    }
}
