package com.example.biletyLotnicze.UI;

import com.example.biletyLotnicze.Models.Klient;
import com.example.biletyLotnicze.Repositories.KlientRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("")
@SpringComponent
@UIScope
public class MainView extends VerticalLayout {

    Klient mainKlient;
    TextField nameField;
    TextField surnameField;
    Button button;
    Label info;

    @Autowired
    BiletView biletView;

    @Autowired
    private KlientRepository klientRepository;

    @PostConstruct
    public void init(){
        mainKlient = null;
        nameField = new TextField("Imie: ");
        surnameField = new TextField("Nazwisko: ");
        button = new Button("Zapisz");
        info = new Label("");
        button.addClickListener(l -> {
            String name = nameField.getValue();
            String surname = surnameField.getValue();
            if(name.isBlank() || surname.isBlank()){
                info.setText("Błąd");
            }else{
                mainKlient = saveOrGetKlient(name, surname);
                changeToBilet(mainKlient);
            }
        });
        add(nameField, surnameField, button, info);
    }

    public Klient saveOrGetKlient(String name, String surname){
        Klient klient = klientRepository.findByNameAndSurname(name, surname).orElse(null);
        if(klient == null) {
            klient = new Klient(name, surname);
            klientRepository.save(klient);
        }
        return klient;
    }

    public void changeToBilet(Klient klient){
        removeAll();
        biletView.setKlient(klient);
        add(biletView);
    }

}
