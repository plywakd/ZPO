/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zpo1;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Plywak
 */
public interface EmployeeDAO {
    //Return id employee
    Optional<Employee> findOne(Integer id);
    //return all employees
    List<Employee> findAll();
    //Return employee by surname
    Optional<Employee> findByName(String name);
    //Delete employee
    void delete(Employee employee);
    //Add, if exists, update employee
    void save(Employee employee);
}
