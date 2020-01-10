package com.clinic.database.repositories;

import com.clinic.database.Priority;
import com.clinic.database.entitys.Doctor;
import com.clinic.database.entitys.Patient;
import com.clinic.database.entitys.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    List<Recipe>findByDescriptionStartsWithIgnoreCase(String description);
    List<Recipe>getByDoctor(Doctor doctor);
    List<Recipe>findByPatientAndPriorityAndDescriptionStartsWithIgnoreCase(Patient patient, Priority priority, String description);
    List<Recipe>findByPatientAndDescriptionStartsWithIgnoreCase(Patient patient,String description);
}
