/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project.Service;

import com.ZPO.project.Model.Pizza;
import com.ZPO.project.Repository.PizzaRepo;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepo pizzaRepo;
    
    /*@PostConstruct
    public void init(){
        pizzaRepo.save(new Pizza(0l,"Margherita",17.0,30,"Sauce,Cheese"));
        pizzaRepo.save(new Pizza(0l,"Vesuvio",19.5,30,"Sauce,Ham,Cheese"));
    }*/
}
