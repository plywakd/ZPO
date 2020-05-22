package com.demo2.services;

import com.demo2.models.Doctor;
import com.demo2.models.Patient;
import com.demo2.models.Visit;
import com.demo2.repositories.DoctorRepository;
import com.demo2.repositories.PatientRepository;
import com.demo2.repositories.VisitRepository;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private VisitRepository visitRepo;

    //@PostConstruct
    public void init() {
//        Doctor d1 = new Doctor("Jan", "Kowalski", "kardiolog");
//        Doctor d2 = new Doctor("Maciej", "Nowak", "proktolog");
//        Doctor d3 = new Doctor("Krzysztof", "Nowakowski", "otolaryngolog");
//        Patient p1 = new Patient("Sulecki", 990128, false);
//        Patient p2 = new Patient("XXX", 123456, true);
//        //Visit v1 = new Visit(1, LocalDate.of(2020, 05, 21), d1, p1);
//
//        doctorRepo.save(d1);
//        doctorRepo.save(d2);
//        doctorRepo.save(d3);
//        patientRepo.save(p1);
//        patientRepo.save(p2);
//        //visitRepo.save(v1);

    }
}
