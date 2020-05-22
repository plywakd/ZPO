package com.example.SpringTest1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseController {
    @Autowired
    EmployeeRepo repo;

    public Iterable<Employee> getEmployees(){
        return repo.findAll();
    }
    public void removeLastEmployee(){
        Employee e = null;
        long i = 1;
        while(repo.findById(i).orElse(null) != null){
            e = repo.findById(i).get();
            i++;
        }
        if(e != null)repo.delete(e);
    }

    public void addEmployee(String name, String email, float salary){
        repo.save(new Employee(name, email, salary));
    }
}
