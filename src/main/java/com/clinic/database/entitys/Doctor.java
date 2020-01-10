package com.clinic.database.entitys;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Doctor extends EntityA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String specialization;

    public Doctor() {
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronymic + ", " + specialization;
    }

    @Override
    public boolean haveNullFields() {
        return firstName == null || lastName == null || patronymic == null || specialization == null;
    }

    @Override
    public void validate() {
        firstName = format(firstName);
        lastName = format(lastName);
        patronymic = format(patronymic);
        specialization = format(specialization);
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = format(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = format(lastName);
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = format(patronymic);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = format(specialization);
    }
}
