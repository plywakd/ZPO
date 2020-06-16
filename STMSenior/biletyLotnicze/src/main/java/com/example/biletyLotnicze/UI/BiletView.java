package com.example.biletyLotnicze.UI;

import com.example.biletyLotnicze.Models.Bilet;
import com.example.biletyLotnicze.Models.Klient;
import com.example.biletyLotnicze.Models.Lot;
import com.example.biletyLotnicze.Repositories.BiletRepository;
import com.example.biletyLotnicze.Repositories.LotRepository;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@UIScope
public class BiletView extends Div {
    @Autowired
    private BiletRepository biletRepository;

    @Autowired
    private LotRepository lotRepository;

    private Klient klient;
    private VerticalLayout layout;
    private Grid<Lot> grid;
    private ComboBox<Bilet> comboBox;
    private Label cena;
    private Label okno;
    private Button confirm;
    private List<Bilet> bilety;

    public BiletView(){
        setSizeFull();
        this.layout = new VerticalLayout();
        grid = new Grid<>(Lot.class);
        layout.setSizeFull();
        add(layout);
        bilety = new ArrayList<>();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        confirm = new Button();
        cena = new Label("");
        okno = new Label("");
        confirm.setVisible(false);
        comboBox = new ComboBox<>();
        comboBox.setAllowCustomValue(false);
        comboBox.setItemLabelGenerator(b -> String.valueOf(b.getMiejsce()));
        comboBox.setItems((b, s) -> b.getKlient()==null, bilety);
        comboBox.addValueChangeListener(l -> {
            if(l.getValue() != null) {
                cena.setText("Cena: " + l.getValue().getCena());
                cena.setVisible(true);
                okno.setText("Przy oknie: " + l.getValue().isPrzyOknie());
                okno.setVisible(true);
                layout.remove(confirm);
                confirm = createConfirmButton(l.getValue());
                layout.add(confirm);
                confirm.setVisible(true);
            }
        });
        grid.setItems(lotsToList(lotRepository.findAll()));
        grid.addItemClickListener(l -> {
            gridClickItemAction(l.getItem());
        });
        DatePicker picker = new DatePicker();
        picker.addValueChangeListener(e -> {
            changeGrid(lotRepository.findByData(e.getValue()));
        });
        layout.add(grid);
        layout.add(picker);
        layout.add(comboBox);
        layout.add(cena, okno, confirm);
    }

    public Button createConfirmButton(Bilet bilet){
        return new Button("Confirm", l ->{
            bilet.setKlient(klient);
            biletRepository.save(bilet);
            setComboBox(bilet.getLot());
            hideConfirm();
        });
    }

    public void gridClickItemAction(Lot lot){
        setComboBox(lot);
        hideConfirm();
    }

    public void hideConfirm(){
        cena.setVisible(false);
        okno.setVisible(false);
        confirm.setVisible(false);
    }

    public void setComboBox(Lot lot){
        bilety.clear();
        biletRepository.findByLotId(lot.getId()).stream().filter(b -> b.getKlient()==null).forEach(b -> bilety.add(b));
        comboBox.getDataProvider().refreshAll();
        comboBox.setValue(null);
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public void changeGrid(List<Lot> list){
        grid.setItems(list);
    }

    public List<Lot> lotsToList(Iterable<Lot> iterable){
        List<Lot> result = new ArrayList<>();
        iterable.forEach(i -> result.add(i));
        return result;
    }

}
