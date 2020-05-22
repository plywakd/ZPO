package com.demo2.repositories;

import com.demo2.models.Doctor;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    public Optional<Doctor> findByNameAndSurname(String name, String surname);
}
