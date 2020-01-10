package com.clinic.database.editors;

import com.clinic.database.entitys.Doctor;
import com.clinic.database.repositories.DoctorRepository;
import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class DoctorEditor extends Editor {

    private TextField lastName;
    private TextField firstName;
    private TextField patronymic;
    private TextField specialization;

    @Autowired
    public DoctorEditor(DoctorRepository doctorRepository) {

        super(Doctor.class);
        this.repository = doctorRepository;
        Label label = new Label("Добавить врача");
        lastName = new TextField("Фамилия");
        lastName.setStyleName("required");
        firstName = new TextField("Имя");
        firstName.setStyleName("required");
        patronymic = new TextField("Отчество");
        patronymic.setStyleName("required");
        specialization = new TextField("Специализация");
        specialization.setStyleName("required");
        dialog.addComponents(label, lastName, firstName, patronymic, specialization, dialogButtons);
        binder = new Binder<>(Doctor.class);
        binder.bindInstanceFields(this);

    }
}