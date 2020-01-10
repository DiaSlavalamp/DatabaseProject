package com.clinic.database.pages;

import com.clinic.database.editors.PatientEditor;
import com.clinic.database.entitys.Patient;
import com.clinic.database.repositories.PatientRepository;

public class PatientPage extends Page {

    public PatientPage(PatientRepository patientRepository, PatientEditor patientEditor) {
        super(Patient.class, patientRepository, patientEditor);
        grid.setColumns("id","lastName", "firstName",  "patronymic", "phone");
        grid.getColumn("id").setCaption("ID");
        grid.getColumn("lastName").setCaption("Фамилия");
        grid.getColumn("firstName").setCaption("Имя");
        grid.getColumn("patronymic").setCaption("Отчество");
        grid.getColumn("phone").setCaption("Телефон");
    }
}
