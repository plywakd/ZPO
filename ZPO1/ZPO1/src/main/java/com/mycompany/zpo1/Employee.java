/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo1;

/**
 *
 * @author Plywak
 */
public class Employee {
    
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Double salary;
    public Employee(Integer id,String name, String surname,String email, Double salary){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.salary=salary;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Double getSalary() {
        return salary;
    }
    
}
