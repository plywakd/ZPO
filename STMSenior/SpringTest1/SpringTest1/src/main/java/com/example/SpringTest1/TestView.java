package com.example.SpringTest1;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@UIScope
@SpringComponent
@Route("api/test")
public class TestView extends VerticalLayout{

    @Autowired
    LoginView loginView;

    @PostConstruct
    public void init(){
        //setSizeFull();
        add(new Label("xD"));
        add(loginView);
    }
}
