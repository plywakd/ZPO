/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.ZPO06.UI;

import com.mycompany.ZPO06.Repository.PatientRepo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("patient")
public class PatientUI extends VerticalLayout{
    public PatientUI(PatientRepo patientRepo){
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
        Button login = new Button("SignIn/Login");
        add(new Text("Witaj"),fl,login);
        login.addClickListener(click->{
            Optional patient=Optional.of(patientRepo.findByPesel(peselField.getValue()));
                if(patient.isPresent()){
                    add(new Text("Found "));
                }
                else{
                    add(new Text("Adding new Patient "));
                }  
        });
        
        
    }
}
