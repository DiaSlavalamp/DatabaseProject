package com.clinic.database.editors;

import com.clinic.database.entitys.Patient;
import com.clinic.database.repositories.PatientRepository;
import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class PatientEditor extends Editor {

    private TextField lastName;
    private TextField firstName;
    private TextField patronymic;
    private TextField phone;

    @Autowired
    public PatientEditor(PatientRepository patientRepository) {

        super(Patient.class);
        this.repository = patientRepository;
        Label label = new Label("Добавить пациента");
        lastName = new TextField("Фамилия");
        lastName.setStyleName("required");
        firstName = new TextField("Имя");
        firstName.setStyleName("required");
        patronymic = new TextField("Отчество");
        patronymic.setStyleName("required");
        phone = new TextField("Телефон");
        phone.setStyleName("required");
        dialog.addComponents(label, lastName, firstName, patronymic, phone, dialogButtons);
        binder = new Binder<>(Patient.class);
        binder.bindInstanceFields(this);

    }
}
