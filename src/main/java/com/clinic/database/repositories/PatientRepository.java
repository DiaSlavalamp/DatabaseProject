package com.clinic.database.repositories;

import com.clinic.database.entitys.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}
