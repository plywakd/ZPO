/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ZPO06.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route(value="")
public class MainUI extends Div{
    
    
    public MainUI(){
        VerticalLayout content=new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        Button asPatient = new Button("Patient");
        Button asDoctor = new Button("Doctor");
        Label labtest = new Label("Witaj na stonie przychodni");
        asPatient.addClickListener(click->asPatient.getUI().ifPresent(ui->ui.navigate("patient")));
        asDoctor.addClickListener(click->asDoctor.getUI().ifPresent(ui->ui.navigate("doctor")));
        buttons.add(asPatient,asDoctor);
        content.add(labtest);
        add(content,buttons);
    }
    
    
}
