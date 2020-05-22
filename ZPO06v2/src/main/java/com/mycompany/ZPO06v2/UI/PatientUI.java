/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.ZPO06v2.UI;

import com.mycompany.ZPO06v2.Model.Patient;
import com.mycompany.ZPO06v2.Repository.PatientRepo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;
import java.time.LocalDate;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("patient")
public class PatientUI extends VerticalLayout{
    
    TextField peselField = new TextField();
    FormLayout fl= new FormLayout();
    TextField nameField = new TextField();
    TextField surnameField = new TextField();
    TextField ageField = new TextField();
    Text patientLogin = new Text("not logged");
    Text visitLabel = new Text("");
    DatePicker visitDatePicker = new DatePicker();
    TimePicker visitTimePicker = new TimePicker();
        
    public PatientUI(PatientRepo patientRepo){
        nameField.setLabel("Name:");
        surnameField.setLabel("Surname:");
        ageField.setLabel("Age:");    
        peselField.setLabel("PESEL:");
        fl.add(nameField,surnameField,ageField,peselField);
        Button login = new Button("SignIn/Login");
        add(new Text("Witaj"),fl,login);
        login.addClickListener(click->{
            Optional patient=Optional.ofNullable(patientRepo.findByPesel(peselField.getValue()));
                if(patient.isPresent()){
                    patientLogin.setText("Found Patient: ");
                    setVisitMenu();
                }
                else{
                    if(peselChecker()){
                        patientLogin.setText("Adding new Patient ");
                        if(nameField.isEmpty() || surnameField.isEmpty() || ageField.isEmpty() || peselField.isEmpty()){
                            patientLogin.setText("Please input all data!");
                        }
                        else{
                            patientRepo.save(new Patient(0l,
                                    nameField.getValue(),
                                    surnameField.getValue(),
                                    Integer.parseInt(ageField.getValue()),
                                    peselField.getValue()));
                            setVisitMenu();
                        }
                    }
                    else{
                        patientLogin.setText("Wrong pesel value!");
                    }
                }
            add(patientLogin);
        });
    }
    
    public boolean peselChecker(){
        String peselToCheck=peselField.getValue();
        if(peselToCheck.length()!=11){
            return false;
        }
        else{
            char[] pesel=peselToCheck.toCharArray();
            for(char c : pesel){
                if(Character.isAlphabetic(c))return false;
            }
        }
        return true;
    }
    
    public void setVisitMenu(){
        visitLabel.setText("Select a date for your visit");
        LocalDate now = LocalDate.now();
        visitDatePicker.setValue(now);
        visitTimePicker.setMin("07:00");
        visitTimePicker.setMax("16:00");
        fl.add(visitLabel,visitDatePicker,visitTimePicker);
    }
}
    
