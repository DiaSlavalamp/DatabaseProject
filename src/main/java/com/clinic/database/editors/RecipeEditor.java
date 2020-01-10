package com.clinic.database.editors;

import com.clinic.database.Priority;
import com.clinic.database.entitys.Doctor;
import com.clinic.database.entitys.Patient;
import com.clinic.database.entitys.Recipe;
import com.clinic.database.repositories.DoctorRepository;
import com.clinic.database.repositories.PatientRepository;
import com.clinic.database.repositories.RecipeRepository;
import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@SpringComponent
@UIScope
public class RecipeEditor extends Editor {

    private ComboBox<Patient> patient;
    private ComboBox<Doctor> doctor;
    private TextField description;
    private DateField date;
    private DateField expirationDate;
    private ComboBox<Priority> priority;

    @Autowired
    public RecipeEditor(RecipeRepository recipeRepository, PatientRepository patientRepository,
                        DoctorRepository doctorRepository) {

        super(Recipe.class);
        this.repository = recipeRepository;

        Label label = new Label("Добавить новый рецепт");

        String fieldSize = "400pt";

        description = new TextField("Описание");
        description.setWidth(fieldSize);
        description.setStyleName("required");

        patient = new ComboBox<>("Пациент");
        patient.setWidth(fieldSize);
        patient.setStyleName("required");
        ArrayList<Patient> pl = (ArrayList<Patient>) patientRepository.findAll();
        patient.setItems(pl);

        doctor = new ComboBox<>("Врач");
        doctor.setWidth(fieldSize);
        doctor.setStyleName("required");
        ArrayList<Doctor> dl = (ArrayList<Doctor>) doctorRepository.findAll();
        doctor.setItems(dl);

        HorizontalLayout dateLayout = new HorizontalLayout();
        date = new DateField("Дата выписки рецепта");
        date.setPlaceholder("Сегодня");
        date.setWidth("100%");
        expirationDate = new DateField("Дата окончания рецепта");
        expirationDate.setPlaceholder("Выберите дату");
        expirationDate.setWidth("100%");
        expirationDate.setStyleName("required");
        dateLayout.addComponents(date, expirationDate);
        dateLayout.setWidth(fieldSize);

        priority = new ComboBox<>("Приоритет");
        priority.setItems(Priority.NORMAL, Priority.CITO, Priority.STATIM);
        priority.setPlaceholder("Нормальный");
        priority.setWidth(fieldSize);

        dialog.addComponents(label, description, patient, doctor, dateLayout, priority, dialogButtons);
        dialog.setComponentAlignment(dialogButtons, Alignment.BOTTOM_RIGHT);
        binder = new Binder<>(Recipe.class);
        binder.bindInstanceFields(this);
    }
}