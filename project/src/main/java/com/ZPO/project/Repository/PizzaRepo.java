/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project.Repository;

import com.ZPO.project.Model.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends CrudRepository<Pizza,Long>{
    //public Pizza findByName(String name);
}
