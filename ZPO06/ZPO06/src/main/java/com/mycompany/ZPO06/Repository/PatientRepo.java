/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ZPO06.Repository;

import com.mycompany.ZPO06.Model.Patient;
import org.springframework.data.repository.CrudRepository;


public interface PatientRepo extends CrudRepository<Patient, Long>{
    
}
