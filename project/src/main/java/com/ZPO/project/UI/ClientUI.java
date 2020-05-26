/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project.UI;

import com.ZPO.project.Model.Pizza;
import com.ZPO.project.Model.PizzaOrder;
import com.ZPO.project.Repository.PizzaOrderRepo;
import com.ZPO.project.Repository.PizzaRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@UIScope
@Route("order")
public final class ClientUI extends Div{
    
    FormLayout fl = new FormLayout();
    TextField clientName = new TextField();
    TextField address = new TextField();
    TextField zipCode = new TextField();
    Grid<Pizza> pizzas = new Grid<>(Pizza.class);
    Grid<Pizza> orderCart = new Grid<>(Pizza.class);
    List<Pizza> orderList = new ArrayList<>();
    HorizontalLayout hl=new HorizontalLayout();
    Button makeOrder = new Button("Order!");
    
    
    
    public ClientUI(@Autowired PizzaRepo pizzaRepo,PizzaOrderRepo pizzaOrderRepo){
        clientName.setLabel("Your name:");
        address.setLabel("Addres");
        zipCode.setLabel("Zip-Code");
        fl.setResponsiveSteps(
           new ResponsiveStep("25em", 1),
           new ResponsiveStep("32em", 2),
           new ResponsiveStep("40em", 3));
        fl.add(clientName,address,zipCode);
        add(fl);
        List<Pizza> fetchPizzas =StreamSupport.stream(pizzaRepo.findAll().spliterator(), false).collect(Collectors.toList());
        pizzas.setItems(fetchPizzas);
        pizzas.addItemDoubleClickListener(dClick->{
            orderList.add(dClick.getItem());
            orderCart.setItems(orderList);
        });
        orderCart.addItemDoubleClickListener(dClick->{
            orderList.remove(dClick.getItem());
            orderCart.getDataProvider().refreshAll();
        });
        hl.add(pizzas,orderCart);
        makeOrder.addClickListener(click->{
            if(orderList.isEmpty() || address.getValue().isEmpty() || zipCode.getValue().isEmpty()){
                Notification.show("please select pizzas and insert addres with zipcode!");
            }
            else{
                pizzaOrderRepo.save(new PizzaOrder(0l,clientName.getValue(),address.getValue(),zipCode.getValue(),LocalTime.now(),orderList,false));
            orderList.removeAll(orderList);
            orderCart.getDataProvider().refreshAll();
            }
            
        });
        add(hl,makeOrder);
    }
    
   
}
