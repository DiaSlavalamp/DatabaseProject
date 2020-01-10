package com.clinic.database.entitys;

import com.clinic.database.Priority;
import com.clinic.database.entitys.exeptions.BeforeCreationDateException;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Recipe extends EntityA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;
    private LocalDate date;
    private LocalDate expirationDate;

    private Priority priority;

    public Recipe() {
    }

    @Override
    public boolean haveNullFields() {
        if (date == null) {
            this.date = LocalDate.now();
        }
        if (priority == null) {
            this.priority = Priority.NORMAL;
        }
        return description == null || patient == null || doctor == null || expirationDate == null;
    }

    @Override
    public void validate() throws BeforeCreationDateException {
        if (expirationDate.isBefore(date)) {
            throw new BeforeCreationDateException("Expiration date before creation date.");
        } else {
            description = format(description);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
