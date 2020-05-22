package com.demo2.repositories;

import com.demo2.models.Doctor;
import com.demo2.models.Patient;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    public List<Patient> findByHospitalized(Boolean hospitalized);
}
