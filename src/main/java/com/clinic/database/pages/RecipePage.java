package com.clinic.database.pages;

import com.clinic.database.Dialog;
import com.clinic.database.Priority;
import com.clinic.database.entitys.EntityA;
import com.clinic.database.entitys.Patient;
import com.clinic.database.editors.RecipeEditor;
import com.clinic.database.entitys.Recipe;
import com.clinic.database.repositories.PatientRepository;
import com.clinic.database.repositories.RecipeRepository;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import org.springframework.util.StringUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class RecipePage extends Page {

    private RecipeRepository recipeRepository;
    private PatientRepository patientRepository;

    private Dialog fiderDialog;

    protected StyleGenerator expiredChecker = t -> {
        LocalDate now = LocalDate.now();
        if (t instanceof Recipe) {
            if (((Recipe) t).getExpirationDate() != null && ((Recipe) t).getExpirationDate().isBefore(now)) {
                return "expired";
            } else {
                return null;
            }
        } else {
            return null;
        }
    };


    public RecipePage(RecipeRepository recipeRepository, RecipeEditor recipeEditor, PatientRepository patientRepository) {

        super(Recipe.class, recipeRepository, recipeEditor);
        this.recipeRepository = recipeRepository;
        this.patientRepository = patientRepository;

        recipeGridCompose(grid);

        TextField filter = new TextField();
        filter.setPlaceholder("Быстрый поиск");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listEntityA(e.getValue()));

        Button findBtn = new Button("Поиск");
        createFinder();
        findBtn.addClickListener(e -> fiderDialog.open());

        editor.editorLabel.addComponents(findBtn, filter);
    }

    private void listEntityA(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems((Collection<Recipe>) repository.findAll());
        } else {
            RecipeRepository r = (RecipeRepository) repository;
            grid.setItems(r.findByDescriptionStartsWithIgnoreCase(filterText));
        }
    }

    private void createFinder() {
        fiderDialog = new Dialog();
        fiderDialog.setDialogWidth("95%");
        fiderDialog.setDialogHeight("95%");

        Label label = new Label("Поиск по рецептам");

        ComboBox<Patient> patient = new ComboBox<>("Пациент");
        patient.setWidth("400pt");
        ArrayList<Patient> ap = (ArrayList<Patient>) patientRepository.findAll();
        patient.setItems(ap);

        ComboBox<Priority> priority = new ComboBox<>("Приоритет");
        priority.setItems(Priority.NORMAL, Priority.CITO, Priority.STATIM);

        TextField description = new TextField("Описание");
        description.setPlaceholder("");
        description.setValueChangeMode(ValueChangeMode.EAGER);
        description.addValueChangeListener(e -> listEntityA(e.getValue()));

        Button apply = new Button("Применить");

        HorizontalLayout fields = new HorizontalLayout();
        fields.addComponents(patient, priority, description, apply);
        fields.setComponentAlignment(apply, Alignment.BOTTOM_LEFT);//Выравнивание кнопки "Применить"

        Grid<Recipe> dialogGrid = new Grid<>(Recipe.class);
        dialogGrid.setWidth("100%");
        dialogGrid.setHeight("100%");
        Collection c = (Collection<EntityA>) repository.findAll();
        dialogGrid.setItems(c);
        recipeGridCompose(dialogGrid);

        apply.addClickListener(e -> {
            if (priority.getValue() != null) {
                dialogGrid.setItems(recipeRepository.findByPatientAndPriorityAndDescriptionStartsWithIgnoreCase(
                        patient.getValue(), priority.getValue(), description.getValue()));
            } else {
                dialogGrid.setItems(recipeRepository.findByPatientAndDescriptionStartsWithIgnoreCase(
                        patient.getValue(), description.getValue()));
            }
        });

        fiderDialog.addComponents(label, fields, dialogGrid);
        fiderDialog.setExpandRatio(dialogGrid, 1);
    }

    private Grid<Recipe> recipeGridCompose(Grid<Recipe> grid) {
        grid.setColumns("id", "description", "patient", "doctor", "date", "expirationDate", "priority");
        grid.getColumn("id").setCaption("ID");
        grid.getColumn("description").setCaption("Описание");
        grid.getColumn("patient").setCaption("Пациент");
        grid.getColumn("doctor").setCaption("Врач");
        grid.getColumn("date").setCaption("Дата создания");
        grid.getColumn("expirationDate").setCaption("Истекает");
        grid.getColumn("priority").setCaption("Приоритет");
        grid.setStyleGenerator(expiredChecker);
        return grid;
    }

}