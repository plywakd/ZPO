package com.example.SpringTest1;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class LoginView extends Div {
    Text infoText;
    TextField login;
    PasswordField password;
    VerticalLayout layout;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        getParent().ifPresent(p -> p.getChildren()
                .filter(c -> !c.equals(this))
                .forEach(c -> c.setVisible(false)));
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        getParent().ifPresent(p -> p.getChildren()
                .forEach(c -> c.setVisible(true)));
    }

    public LoginView(){
        layout = new VerticalLayout();
        infoText = new Text("");
        login = new TextField();
        login.setLabel("Login: ");
        password = new PasswordField();
        password.setLabel("Password: ");
        Button button = new Button("Login");
        button.addClickListener(e -> {
            String loginString = login.getValue();
            String passwordString = password.getValue();
            verify(loginString, passwordString);
        });
        Button clear = new Button("Clear", e -> {
            infoText.setText("");
            login.setValue("");
            password.setValue("");
        });
        layout.add(new Span(login), new Span(password), new Span(button, new Label(" "),  clear), new Span(infoText));
        add(layout);
    }

    //Adapt to check login and password
    private void verify(String login, String password){
        userRepository.findById(login)
                .ifPresentOrElse(user -> {
                    if(user.getPassword().equals(password)){
                        loginSucceedAction();
                    }else{
                        loginFailedAction();
                    }
                }, () -> loginFailedAction());
    }

    //Adapt to specify action if login succeed
    private void loginSucceedAction(){
        VerticalLayout parent = (VerticalLayout)getParent().get();
        parent.remove(this);
    }

    //Adapt to specify action id login failed
    private void loginFailedAction(){
        infoText.setText("Login or password incorrect");
    }
}
