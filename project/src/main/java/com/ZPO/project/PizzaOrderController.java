/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project;

import com.ZPO.project.Model.PizzaOrder;
import com.ZPO.project.Repository.PizzaOrderRepo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaOrderController {
    
    @Autowired 
    PizzaOrderRepo pizzaOrderRepo;
    
    @GetMapping("/genReport")
    public Map<Integer,Double> genReport(){
        Map<Integer,Double> report = new HashMap<>();
        List<PizzaOrder> allOrders= StreamSupport.stream(pizzaOrderRepo.findAll().spliterator(), false).collect(Collectors.toList());
        report = allOrders.stream().collect(Collectors.groupingBy(p->p.getOrderTime().getHour(),Collectors.summingDouble(p->p.getPizza().stream().mapToDouble(pi->pi.getPrice()).sum())));
        return report;
    }
}
