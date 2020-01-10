package com.clinic.database.entitys;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Patient extends EntityA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;

    public Patient() {
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronymic + ", " + phone;
    }

    @Override
    public boolean haveNullFields() {
        return firstName == null || lastName == null || patronymic == null || phone == null;
    }

    @Override
    public void validate() {
        firstName = format(firstName);
        lastName = format(lastName);
        patronymic = format(patronymic);
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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
