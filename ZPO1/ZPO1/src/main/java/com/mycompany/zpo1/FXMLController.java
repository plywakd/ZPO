package com.mycompany.zpo1;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable,EmployeeDAO {
    
    DbConnector connector= new DbConnector();
    Connection con;
    ObservableList<Employee> tableList = FXCollections.observableArrayList();
    @FXML
    private Label tableName;
    @FXML
    private TableView<Employee> sqlTable;
    @FXML
    private TableColumn<Employee,Integer> colId;
    @FXML
    private TableColumn<Employee,String> colName;
    @FXML
    private TableColumn<Employee,String> colSurname;
    @FXML
    private TableColumn<Employee,String> colEmail;
    @FXML
    private TableColumn<Employee,Double>colSalary;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private TextField salary;
    @FXML
    private TextField idOrName;
    @FXML
    private Button addEmployee;
    @FXML
    private Button delEmployee;
    @FXML
    private ChoiceBox searchOpt;
    @FXML
    private void handleButtonAction(ActionEvent event) throws ClassNotFoundException{
        Button button =(Button)event.getSource();
        String buttonId = button.getId();
        String query;
        con= connector.connect();
        switch(buttonId){
            case("addEmployee"):
                query = "INSERT INTO \"Employees\" (id,name,surname,email,salary) VALUES("+Integer.parseInt(id.getText())+",\'"+name.getText()+"\',\'"+surname.getText()+"\',\'"+email.getText()+"\',"+Double.parseDouble(salary.getText())+")";
                connector.sendQuery(query);
                save(new Employee(
                        Integer.parseInt(id.getText()),
                        name.getText(),
                        surname.getText(),
                        email.getText(),
                        Double.parseDouble(salary.getText())
                ));
                break;
            case("delEmployee"):
                Employee deleteEmployee=sqlTable.getSelectionModel().getSelectedItem();
                query="DELETE FROM \"Employees\" WHERE id="+deleteEmployee.getId();
                System.out.println(deleteEmployee.getId());
                connector.sendQuery(query);
                delete(deleteEmployee);
                break;
            case("updateEmployee"):
                query="UPDATE \"Employees\" SET name=\'"+name.getText()+"\',surname=\'"+surname.getText()+"\',email=\'"+email.getText()+"\',salary="+salary.getText()+" WHERE id="+id.getText();
                connector.sendQuery(query);
                save(new Employee(
                        Integer.parseInt(id.getText()),
                        name.getText(),
                        surname.getText(),
                        email.getText(),
                        Double.parseDouble(salary.getText())
                ));
                break;
            case("searchEmployee"):
                System.out.println(searchOpt.getSelectionModel().getSelectedItem());
                if(searchOpt.getSelectionModel().getSelectedItem().equals("ID")){
                    findOne(Integer.parseInt(idOrName.getText()));
                }else{
                    findByName(idOrName.getText());
                }
                break;
        }
    }
    @Override
    public Optional<Employee> findOne(Integer id) {
        Employee result=tableList.stream().filter(Employee->Employee.getId().equals(id)).findFirst().orElse(null);// Optional<Employee> throws cant be converted to Employee ? 
        sqlTable.getSelectionModel().select(result);
        Optional<Employee> res = Optional.of(result);//for return
        return res;
    }
    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList= new ArrayList();
        String query = ("SELECT * from \"Employees\"");            
        ResultSet rs;
        try {
            rs = connector.getResultDb(query);
            while(rs.next()){
            employeeList.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getDouble("salary"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return employeeList;
    }
    @Override
    public Optional<Employee> findByName(String name) {
         Employee result=tableList.stream().filter(Employee->Employee.getName().equals(name)).findFirst().orElse(null);// Optional<Employee> throws cant be converted to Employee ? 
         Optional<Employee> res = Optional.of(result);//for return
        sqlTable.getSelectionModel().select(result);
        return res;
    }
    @Override
    public void delete(Employee employee) {
        String query="DELETE * FROM WHERE id="+employee.getId();
        tableList.remove(employee);
    }
    @Override
    public void save(Employee employee) {
        Employee existed=tableList.stream().filter(Employee->Employee.getId().compareTo(employee.getId())==0).findAny().orElse(null);
        if(existed==null)tableList.add(employee);
        else {
            delete(existed);
            tableList.add(employee);
        }  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         searchOpt.setItems(FXCollections.observableArrayList(
    "ID", "Name"));
        colId.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<Employee,String>("surname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Employee,String>("email"));
        colSalary.setCellValueFactory(new PropertyValueFactory<Employee,Double>("salary"));
        try{
            con= connector.connect();
            String query = ("SELECT * from \"Employees\"");            
            ResultSet rs = connector.getResultDb(query);
            while(rs.next()){
                save(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getDouble("salary"))
                );
            }
            sqlTable.setItems(tableList);
            System.out.println("Fetched data");
        }catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }    
}