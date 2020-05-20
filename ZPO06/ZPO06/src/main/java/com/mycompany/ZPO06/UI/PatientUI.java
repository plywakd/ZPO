/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.ZPO06.UI;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("patient")
public class PatientUI extends VerticalLayout{
    public PatientUI(){
        FormLayout fl= new FormLayout();
        TextField nameField = new TextField();
        nameField.setLabel("Name:");
        TextField surnameField = new TextField();
        surnameField.setLabel("Surname:");
        TextField ageField = new TextField();
        ageField.setLabel("Age:");
        TextField peselField = new TextField();
        peselField.setLabel("PESEL:");
        fl.add(nameField,surnameField,ageField,peselField);
        add(new Text("Witaj"),fl);
    }
}
