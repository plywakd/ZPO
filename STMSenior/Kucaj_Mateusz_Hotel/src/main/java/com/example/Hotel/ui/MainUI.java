package com.example.Hotel.ui;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Hotel.models.Booking;
import com.example.Hotel.models.Client;
import com.example.Hotel.models.Room;
import com.example.Hotel.reposytory.BookingRepository;
import com.example.Hotel.reposytory.ClientRepository;
import com.example.Hotel.reposytory.RoomRepository;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;

@SpringUI(path = "")
public class MainUI extends UI {

    private static final long serialVersionUID = 2013634941609102698L;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientRepository clientRepository;

    public boolean check(String s) {

        try {
            @SuppressWarnings("unused")
            int i = Integer.valueOf(s);


            return false;
        } catch (Exception x) {

            return true;
        }

    }

    @Override
    protected void init(VaadinRequest request) {

        HorizontalLayout root = new HorizontalLayout();
        root.setSizeFull();

        TextField name = new TextField("Podaj Imie:");
        TextField surname = new TextField("Podaj Nazwisko:");
        TextField size = new TextField("Podaj liczbe osob:");
        CheckBox view = new CheckBox("Ładny widok");
        DateField start = new DateField("Termin pobytu od: ");
        DateField end = new DateField("do");

        Button doReservation = new Button("Rezerwuj");

        doReservation.addClickListener(ClickEvent -> {

            String n = name.getValue();
            String s = surname.getValue();
            Integer si = Integer.parseInt(size.getValue());
            boolean v = view.getValue();
            LocalDate o = start.getValue();
            LocalDate e = end.getValue();
            List<Room> r = roomRepository.findAll().stream()
                    .filter(siz -> siz.getSize().equals(si))
                    .filter(vie -> vie.isView() == v)
                    .collect(Collectors.toList());
            Room room = r.stream().min(Comparator.comparingDouble(Room::getPrice)).orElseThrow(NoSuchElementException::new);
            //Room room = r.get(0);
            if (check(n) && check(s) && !check(size.getValue())) {
                if (clientRepository.findAll().stream().anyMatch(a -> a.getName().equals(n) && a.getSurname().equals(s))) {

                    if (checkDate(room, o, e)) {
                        Notification.show("Nie ma dostepnych pokoi z takimi warunkami", Notification.Type.WARNING_MESSAGE);
                    } else {

                        clientRepository.findAll().stream()
                                .filter(c -> c.getName().equals(n))
                                .filter(x -> x.getSurname().equals(s)).forEach(
                                y -> {
                                    Booking b = new Booking(0l, o, e, false, y, room);
                                    b = bookingRepository.save(b);
                                    Notification.show("Zarezerwowałeś pokój numer " + room.getId(), Notification.Type.WARNING_MESSAGE);
                                }
                        );
                    }
                } else {
                    if (checkDate(room, o, e)) {
                        Notification.show("Nie ma dostepnych pokoi z takimi warunkami", Notification.Type.WARNING_MESSAGE);

                    } else {
                        Client c = new Client(0l, name.getValue(), surname.getValue());
                        c = clientRepository.save(c);
                        Booking b = new Booking(0l, o, e, false, c, room);
                        b = bookingRepository.save(b);
                        Notification.show("Zarezerwowałeś pokój numer " + room.getId(), Notification.Type.WARNING_MESSAGE);
                    }
                }
            } else {
                Notification.show("Cyfry w imieniu i nazwisku", Notification.Type.WARNING_MESSAGE);
            }

        });

        FormLayout form = new FormLayout();
        form.addComponents(name, surname, size, view, start, end, doReservation);
        root.addComponent(form);
        setContent(root);
    }

    public boolean checkDate(Room room, LocalDate o, LocalDate e) {
        return bookingRepository.findAll().stream()
                .filter(st -> st.getRoom().equals(room))
                .filter(bo -> bo.getEnd().compareTo(o) > 0)
                .filter(ne -> ne.getStart().compareTo(e) < 0)
                .anyMatch(st -> st.getRoom().equals(room));
    }

}
