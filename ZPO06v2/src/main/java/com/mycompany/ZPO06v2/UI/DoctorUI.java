/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.ZPO06v2.UI;

import com.mycompany.ZPO06v2.Model.Doctor;
import com.mycompany.ZPO06v2.Model.Visit;
import com.mycompany.ZPO06v2.Repository.DoctorRepo;
import com.mycompany.ZPO06v2.Repository.VisitRepo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Route("doctor")
public class DoctorUI extends Div{
    
    VerticalLayout vl = new VerticalLayout();
    Button test = new Button("Show visits");
    TextField nameField = new TextField();
    TextField surnameField = new TextField();
    TextField specializationField = new TextField();
    
    @Autowired
    DoctorRepo doctorRepo;
    
    @Autowired
    VisitRepo visitRepo;
    
    public DoctorUI(){
        nameField.setLabel("Name:");
        surnameField.setLabel("Surname");
        specializationField.setLabel("Spec");
        vl.add(nameField,surnameField,specializationField,test);
        test.addClickListener(click->{
            Doctor doc=checkDoctor();
                Optional loggedDoc=Optional.ofNullable(checkDoctor());
                if(loggedDoc.isPresent()){
                    List<Visit> docVis=StreamSupport.stream(visitRepo.findAll().spliterator(),false).filter(v->v.getIdDoctor().equals(doc.getId())).collect(Collectors.toList());
                    log.info(docVis.toString());
                }
        });
        add(vl);
    }
    
    public Doctor checkDoctor(){
        List<Doctor> docs = doctorRepo.findBySpecialization(specializationField.getValue()).stream().
                filter(d->d.getName().equals(nameField.getValue())).
                filter(d->d.getSurname().equals(surnameField.getValue())).
                collect(Collectors.toList());
        return docs.get(0);
    }
}
