package com.clinic.database;

import com.clinic.database.editors.DoctorEditor;
import com.clinic.database.editors.PatientEditor;
import com.clinic.database.editors.RecipeEditor;
import com.clinic.database.pages.DoctorPage;
import com.clinic.database.pages.PatientPage;
import com.clinic.database.pages.RecipePage;
import com.clinic.database.repositories.DoctorRepository;
import com.clinic.database.repositories.PatientRepository;
import com.clinic.database.repositories.RecipeRepository;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.*;

@JavaScript({"bg.jpg"})
@StyleSheet("styles.css")
@SpringUI
public class View extends UI {

    private PatientRepository patientRepository;
    private PatientEditor patientEditor;
    private DoctorRepository doctorRepository;
    private DoctorEditor doctorEditor;
    private RecipeRepository recipeRepository;
    private RecipeEditor recipeEditor;

    public View(PatientRepository patientRepository, PatientEditor patientEditor, DoctorRepository doctorRepository, DoctorEditor doctorEditor, RecipeRepository recipeRepository, RecipeEditor recipeEditor) {
        this.patientRepository = patientRepository;
        this.patientEditor = patientEditor;
        this.doctorRepository = doctorRepository;
        this.doctorEditor = doctorEditor;
        this.recipeRepository = recipeRepository;
        this.recipeEditor = recipeEditor;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {


        VerticalLayout body = new VerticalLayout();
        body.setHeight("100%");
        setContent(body);

        PatientPage patientPage = new PatientPage(patientRepository, patientEditor);
        DoctorPage doctorPage = new DoctorPage(doctorRepository, doctorEditor, recipeRepository);
        RecipePage recipePage = new RecipePage(recipeRepository, recipeEditor, patientRepository);

        TabSheet tabs = new TabSheet();
        tabs.addTab(patientPage, "Пациенты");
        tabs.addTab(doctorPage, "Врачи");
        tabs.addTab(recipePage, "Рецепты");
        tabs.setHeight("100%");

        body.addComponent(tabs);

    }
}
