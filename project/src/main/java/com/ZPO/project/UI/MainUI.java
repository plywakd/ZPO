/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@Route("")
public class MainUI extends Div{
    
    HorizontalLayout content = new HorizontalLayout();
    Button makeOrder = new Button("Make Order");
    Button logAsCook = new Button("Login as Cook");
    Label welcome = new Label("Spring pizzas!");
    
    
    public MainUI(){
        
        makeOrder.addClickListener(click->
            makeOrder.getUI().ifPresent(ui->ui.navigate("order")));
        logAsCook.addClickListener(click->
            logAsCook.getUI().ifPresent(ui->ui.navigate("cook")));
        content.add(makeOrder,logAsCook);
        add(welcome,content);
    }
}
