/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ZPO06v2.Service;


import com.mycompany.ZPO06v2.Model.Visit;
import com.mycompany.ZPO06v2.Repository.VisitRepo;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VisitService {
    @Autowired
    private VisitRepo visitRepo;
    
    
    
}
