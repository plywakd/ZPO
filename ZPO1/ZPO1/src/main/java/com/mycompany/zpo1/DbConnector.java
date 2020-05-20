/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 *
 * @author Plywak
 */
public class DbConnector {
    private final String postgresUrl="jdbc:postgresql://localhost/ZPO1";
    private final Properties property=new Properties();
    private final String user="postgres";
    private final String password="Qwerty123456";
    private Connection con=null;
    private ResultSet rs=null;
    private Statement sqlStatement=null;
    public Connection connect() throws ClassNotFoundException{
        try{
            Class.forName("org.postgresql.Driver");
            property.setProperty("user",user);
            property.setProperty("password",password);
            con = DriverManager.getConnection(postgresUrl, property);
            sqlStatement=con.createStatement();
            return con;
        }catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return null;
        }
    }
    public void sendQuery(String query){
        try {
            sqlStatement.executeUpdate(query);
        } catch (SQLException ex) {
        System.out.println("błąd SQL");
        }
    }
    public ResultSet getResultDb(String query) throws SQLException{
        sqlStatement.execute(query);
        rs=sqlStatement.getResultSet();
        return rs;
    }
    
}
