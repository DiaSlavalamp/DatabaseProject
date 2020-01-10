package com.clinic.database.repositories;

import com.clinic.database.entitys.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
}