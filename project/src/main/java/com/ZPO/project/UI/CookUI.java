/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project.UI;

import com.ZPO.project.Model.Pizza;
import com.ZPO.project.Model.PizzaOrder;
import com.ZPO.project.Repository.PizzaOrderRepo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@UIScope
@Route("cook")
public class CookUI extends Div{
    
    
    Grid<PizzaOrder> orders = new Grid<>(PizzaOrder.class);
    
    public CookUI(@Autowired PizzaOrderRepo pizzaOrderRepo){
        List<PizzaOrder> allOrders=StreamSupport.stream(pizzaOrderRepo.findByReady(false).spliterator(), false).collect(Collectors.toList());
        orders.setItems(allOrders);
        add(orders);
        orders.addItemClickListener(click->{
            Notification.show(click.getItem().getPizza().toString());
        });
        orders.addItemDoubleClickListener(dClick->{
            pizzaOrderRepo.setPizzaOrderReady(true, dClick.getItem().getId());
            allOrders.remove(dClick.getItem());
            orders.getDataProvider().refreshAll();
        });
    }
    
    
}
