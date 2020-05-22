package com.demo2.UI;

import com.demo2.models.Doctor;
import com.demo2.models.Visit;
import com.demo2.repositories.DoctorRepository;
import com.demo2.repositories.VisitRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@SpringComponent
@UIScope
public class MainView extends VerticalLayout {

    @Autowired
    private DoctorRepository doctorRepo;
    
    @Autowired
    private VisitRepository visitRepo;

    private TextField nameField;
    private TextField surnameField;
    private Button applyButton;
    private Label infoLabel;
    private Doctor mainDoctor;
    private Grid<Visit> grid;
    private Checkbox hospitalizedCheckbox;

    @PostConstruct
    public void init() {
        mainDoctor = null;
        nameField = new TextField("Imię:");
        surnameField = new TextField("Nazwisko:");
        applyButton = new Button("Sprawdz wizyty");
        infoLabel = new Label("");
        grid=new Grid<>(Visit.class);
        hospitalizedCheckbox=new Checkbox();
        hospitalizedCheckbox.setLabel("Czy hospitalizowany?");
        hospitalizedCheckbox.setValue(false);

        applyButton.addClickListener(listener -> {
            if (nameField.isEmpty() || surnameField.isEmpty()) {
                infoLabel.setText("Wpisz poprawnie dane");
            } else {
                String name = nameField.getValue();
                String surname = surnameField.getValue();
                mainDoctor = getMainDoctor(name, surname);
                if (mainDoctor != null) {
                    insertComps(mainDoctor);
                }else{
                    grid.setItems();
                    grid.setVisible(false);
                }
            }
        });
        add(nameField, surnameField, applyButton, infoLabel, hospitalizedCheckbox);
        
        hospitalizedCheckbox.addValueChangeListener(e -> {
            if(hospitalizedCheckbox.getValue()==true){//if true
                grid.getItems();
            }
            else{
                
            }
        });
    }

    public Doctor getMainDoctor(String name, String surname) {
        Doctor doctor = doctorRepo.findByNameAndSurname(name, surname).orElse(null);
        if (doctor == null) {
            infoLabel.setText("Nie ma takiego doktora");
        } else {
            infoLabel.setText("");
        }
        return doctor;
    }
    
    private void insertComps(Doctor doctor) {
        grid.setItems(visitRepo.findByDoctorId(doctor.getId()));
        add(grid);
        
    }


}
