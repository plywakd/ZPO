/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project;

import com.ZPO.project.Model.Pizza;
import com.ZPO.project.Model.PizzaOrder;
import com.ZPO.project.Repository.PizzaOrderRepo;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaOrderControllerTest {
    
    @Autowired 
    private PizzaOrderRepo pizzaOrderRepo;
    @Autowired
    private PizzaOrderController pizzaOrderController;
    /**
     * Test of genReport method, of class PizzaOrderController.
     */
    @Test
    public void testGenReport() {
        System.out.println("genReport");
        List<Pizza> pizzas=new ArrayList();
        pizzas.add(new Pizza(1l,"Marg",17.0,30,"Sauce,Cheese"));
        pizzaOrderRepo.save(new PizzaOrder(1l,"Baba","Test 8","00-090",LocalTime.of(10,11),pizzas,false));
        pizzas.removeAll(pizzas);
        pizzas.add(new Pizza(2l,"Ves",19.5,30,"Sauce,Ham,Cheese"));
        pizzaOrderRepo.save(new PizzaOrder(2l,"Baba","Tesoooowa","00-090",LocalTime.of(10,11), pizzas ,false));
        Map<Integer, Double> expResult = new HashMap<>();
        expResult.put(10, 36.5);
        Map<Integer, Double> result = pizzaOrderController.genReport();
        assertEquals(expResult, result);

    }
    
}
