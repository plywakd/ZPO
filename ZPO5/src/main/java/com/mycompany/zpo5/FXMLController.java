package com.mycompany.zpo5;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button generateObject,executeObjMethod,setObjValue;
    @FXML
    private TextField className,newObjValue;
    @FXML
    private ListView objectMethods,objectParams;
    
    ObservableList<String> methods = FXCollections.observableArrayList ();
    ObservableList<String> params = FXCollections.observableArrayList ();
    Class cls;
    Object clsInstance;
    
    public void createInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(!className.getText().isEmpty()){
            String clsName = className.getText();
            cls = Class.forName(clsName);
            clsInstance = (Object) cls.newInstance();
        }
    }
    
    public void getClassMethods(){
        Method[] objMethods = cls.getDeclaredMethods();
            for( Method method : objMethods){
                StringBuilder methodName=new StringBuilder();
                methodName.append(method.getName());
                methodName.append(Arrays.toString(method.getParameterTypes()).replace("[", "(").replace("]", ")"));
                methods.add(methodName.toString());
            }
            objectMethods.setItems(methods);
    }
    
    public void getClassFields() throws IllegalArgumentException, IllegalAccessException{
        objectParams.getItems().clear();
        Field[] objFields=cls.getDeclaredFields();
            for(Field field : objFields){
                field.setAccessible(true);
                StringBuilder fieldNameAndValue=new StringBuilder();
                fieldNameAndValue.append(field.getName());
                fieldNameAndValue.append(": ");
                fieldNameAndValue.append(field.get(clsInstance).toString());
                params.add(fieldNameAndValue.toString());
            }
            objectParams.setItems(params);
    }
    
    public void executeMethod() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String methodName=objectMethods.getSelectionModel().getSelectedItem().toString();
        methodName=methodName.substring(0,methodName.indexOf("("));
        Method methodToInvoke=cls.getDeclaredMethod(methodName);
        methodToInvoke.setAccessible(true);
        System.out.println(methodToInvoke.invoke(clsInstance));
    }
    
    public void setNewValue() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException{
        String newValue=newObjValue.getText();
        StringBuilder methodName=new StringBuilder();
        methodName.append("set");
        String method=objectParams.getSelectionModel().getSelectedItem().toString();
        methodName.append(method.substring(0, 1).toUpperCase());
        methodName.append(method.substring(1,method.indexOf(":")));
        String findMethod=methods.stream()
                .filter(o->o.contains(methodName))
                .map(Object::toString)
                .collect(Collectors.joining());
        if(!findMethod.isEmpty()){
            String setterType=findMethod.substring(findMethod.indexOf("(")+1, findMethod.indexOf(")"));
            Method methodToInvoke = null;
            switch(setterType){
                case "int":
                    methodToInvoke=cls.getDeclaredMethod(methodName.toString(),int.class);
                    methodToInvoke.invoke(clsInstance,Integer.parseInt(newValue));
                    break;
                case "boolean":
                    methodToInvoke=cls.getDeclaredMethod(methodName.toString(),boolean.class);
                    methodToInvoke.invoke(clsInstance,Integer.parseInt(newValue));
                    break;
                case "double":
                    methodToInvoke=cls.getDeclaredMethod(methodName.toString(),double.class);
                    methodToInvoke.invoke(clsInstance,Double.parseDouble(newValue));
                    break;
                case "float":
                    methodToInvoke=cls.getDeclaredMethod(methodName.toString(),float.class);
                    methodToInvoke.invoke(clsInstance,Float.parseFloat(newValue));
                    break;
                case "class java.lang.String":
                    methodToInvoke=cls.getDeclaredMethod(methodName.toString(), String.class);
                    methodToInvoke.invoke(clsInstance,newValue);
                    break;
                case "class java.lang.Enum":
                    methodToInvoke=cls.getDeclaredMethod(methodName.toString(), Enum.class);
                    methodToInvoke.invoke(clsInstance,newValue);
                    break;
            }
            getClassFields();
        }else{
            System.out.println("Method setter doesnt exist!");
        } 
    }
        
    @FXML
    private void handleButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Button button =(Button)event.getSource();
        String buttonId = button.getId();
        switch(buttonId){
            case "generateObject":
                createInstance();
                getClassMethods();
                getClassFields();
                break;
            case "executeObjMethod":
                executeMethod();
                break;
            case "setObjValue":
                setNewValue();
                break;
        }     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
