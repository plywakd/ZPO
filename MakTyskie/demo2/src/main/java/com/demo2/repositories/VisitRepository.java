package com.demo2.repositories;

import com.demo2.models.Doctor;
import com.demo2.models.Visit;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {

    @Override
    public List<Visit> findAll();

    public List<Visit> findByDoctorId(Long id);
}
