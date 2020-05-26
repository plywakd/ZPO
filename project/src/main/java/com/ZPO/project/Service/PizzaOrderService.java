/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ZPO.project.Repository.PizzaOrderRepo;

@Service
public class PizzaOrderService {
    @Autowired
    private PizzaOrderRepo pizzaOrderRepo;
}
