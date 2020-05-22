package com.example.SpringTest1;

import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import org.springframework.data.util.ReflectionUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DetailedItemsListDiv {
    Div div;
    Details details;
    ArrayList<String> columnNames;

    public DetailedItemsListDiv() {
        this.columnNames = new ArrayList<>();
        this.div = new Div();
        div.setWidthFull();
    }

    public DetailedItemsListDiv(Class c) {
        this.columnNames = new ArrayList<>();
        this.div = new Div();
        setColumnNames(c);
    }

    public void setColumnNames(Class c){
        for(Field f : c.getDeclaredFields()){
            if(f.getDeclaredAnnotation(Column.class) != null){
                columnNames.add(f.getDeclaredAnnotation(Column.class).name());
            }
        }
    }

    public void addDetailedItem(Employee e){
        details = new Details();
        details.setSummaryText(e.getName());
        details.addContent(new H5("Email: "+e.getEmail()), new H5("Salary: "+e.getSalary()));
        div.add(details);
    }

    public String getValueByColumnName(String column, Employee e){
        String result = "";
        try {
            result = ""+ReflectionUtils.findRequiredMethod(e.getClass(), "get"+column.substring(0,1).toUpperCase()+column.substring(1)).invoke(e);
        } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        return result;
    }

    public Div getDiv(){
        return div;
    }


}
