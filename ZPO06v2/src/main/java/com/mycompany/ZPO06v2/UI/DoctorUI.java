/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.ZPO06v2.UI;

import com.mycompany.ZPO06v2.Repository.DoctorRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("doctor")
public class DoctorUI extends Div{
    VerticalLayout vl = new VerticalLayout();
    Button test = new Button("Show visits");
    public DoctorUI(DoctorRepo doctorRepo){
        vl.add(test);
        add(vl);
    }
    
}
