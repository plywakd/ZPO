/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.ZPO06.UI;

import com.mycompany.ZPO06.Model.Patient;
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
        
        Button login = new Button("SignIn/Login");
        add(new Text("Witaj"),fl,login);
        TextField patientLogin = new TextField();
        login.addClickListener(click->{
            Optional patient=Optional.ofNullable(patientRepo.findByPesel(peselField.getValue()));
                if(patient.isPresent()){
                    patientLogin.setValue("Found Patient: ");
                }
                else{
                    patientLogin.setValue("Adding new Patient ");
                    if(nameField.isEmpty() || surnameField.isEmpty() || ageField.isEmpty() || peselField.isEmpty()){
                        patientLogin.setValue("Please input all data!");
                    }
                    else{
                        patientRepo.save(new Patient(0l,nameField.getValue(),surnameField.getValue(),Integer.parseInt(ageField.getValue()),peselField.getValue()));
                    }
                }
            add(patientLogin);
        });
    }
    
    public void setFormMenu(){
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
    }
    
    public void setVisitMenu(){
        
    }
}
