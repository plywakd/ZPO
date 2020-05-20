package com.mycompany.zpo5b;

import com.mycompany.zpo5b.beans.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private Button createObject;
    @FXML
    private TableView<Bean> objectsTable;
    @FXML
    private TableColumn<Bean,String> field1;
    @FXML
    private TableColumn<Bean,String> field2;
    @FXML
    private TableColumn<Bean,String> field3;
    @FXML
    private TextField className;
    @FXML
    private TextField classField1;
    @FXML
    private TextField classField2;
    @FXML
    private TextField classField3;
    
    ObservableList<Bean> objects = FXCollections.observableArrayList ();
    Class cls;
    Object clsInstance;
    
    public void columnNames(Class cls){
        int fieldCounter=1;
        for(Field field :cls.getDeclaredFields() ){
            if(field.isAnnotationPresent(Named.class) && fieldCounter==1){
                Named an=field.getAnnotation(Named.class);
                field1.setText(an.value());
                fieldCounter++;
            }
            else if(field.isAnnotationPresent(Named.class) && fieldCounter==2){
                Named an=field.getAnnotation(Named.class);
                field2.setText(an.value());
                fieldCounter++;
            }
            else if(field.isAnnotationPresent(Named.class) && fieldCounter==3){
                Named an=field.getAnnotation(Named.class);
                field3.setText(an.value());
                fieldCounter++;
            }
        }
    }
    
    public void addObjToTable() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
        Bean bean=null;
        String classToCreate="com.mycompany.zpo5b.beans."+className.getText();
        cls = Class.forName(classToCreate);
        columnNames(cls);
        if(classField1.getText().isEmpty() || classField2.getText().isEmpty() || classField3.getText().isEmpty()){
            bean=(Bean) cls.getDeclaredConstructor().newInstance();
            System.out.println(bean);
            objects.add(bean);
        }
        else{
            bean=(Bean) cls.getDeclaredConstructor().newInstance();
            modifyObj(bean);
        }
    }
    
    public void modifyObj(Bean beanToModify) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException{
        Field enumField=beanToModify.getClass().getDeclaredField("fEnum");
        enumField.setAccessible(true);
        try{
            if(!classField1.getText().isEmpty()){
                beanToModify.setfBean(classField1.getText());
            }
            if(!classField2.getText().isEmpty()){
                Enum en=Enum.valueOf((Class) enumField.get(beanToModify).getClass(), classField2.getText());
                beanToModify.setfEnum(en);
            }
            if(!classField3.getText().isEmpty()){
                beanToModify.setfDate(LocalDate.parse(classField3.getText()));
            }
            objects.add(beanToModify);
            deleteObj();
        }catch(Exception e){
            System.out.println("Incorrect data in textfields!");
        }
    }
    
    public void deleteObj(){
        objects.remove(objectsTable.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Button button =(Button)event.getSource();
        String buttonId = button.getId();
        switch(buttonId){
            case"createObject":
                addObjToTable();
                break;
            case"modifyObject":
                modifyObj(objectsTable.getSelectionModel().getSelectedItem());
                break;
            case"deleteObject":
                deleteObj();
                break;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO annotations as column names
        field1.setCellValueFactory(param-> new ReadOnlyStringWrapper(param.getValue().getfBean()));
        field2.setCellValueFactory(param-> new ReadOnlyStringWrapper(param.getValue().getfEnum().toString()));
        field3.setCellValueFactory(param-> new ReadOnlyStringWrapper(param.getValue().getfDate().toString()));
        objectsTable.setItems(objects);
    }    
}
