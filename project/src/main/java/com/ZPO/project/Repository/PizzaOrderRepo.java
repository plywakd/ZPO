/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ZPO.project.Repository;

import com.ZPO.project.Model.PizzaOrder;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaOrderRepo extends CrudRepository<PizzaOrder,Long>{
    //public PizzaOrder findByName(String name);
    public List<PizzaOrder> findByReady(boolean ready);
    @Modifying
    @Transactional
    @Query("update PizzaOrder p set p.ready = ?1 where p.id = ?2")
    public void setPizzaOrderReady(boolean ready,Long pizzaOrderId);
}
