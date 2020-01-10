package com.clinic.database.pages;

import com.clinic.database.Dialog;
import com.clinic.database.entitys.Doctor;
import com.clinic.database.editors.DoctorEditor;
import com.clinic.database.entitys.Recipe;
import com.clinic.database.repositories.DoctorRepository;
import com.clinic.database.repositories.RecipeRepository;
import com.vaadin.ui.*;

public class DoctorPage extends Page {

    private RecipeRepository recipeRepository;

    public DoctorPage(DoctorRepository doctorRepository, DoctorEditor doctorEditor,
                      RecipeRepository recipeRepository) {

        super(Doctor.class, doctorRepository, doctorEditor);
        this.recipeRepository = recipeRepository;

        Button statistics = new Button("Показать статистику");
        statistics.addClickListener(e -> getStatistics());

        grid.setColumns("id", "lastName", "firstName", "patronymic", "specialization");
        grid.getColumn("id").setCaption("ID");
        grid.getColumn("lastName").setCaption("Фамилия");
        grid.getColumn("firstName").setCaption("Имя");
        grid.getColumn("patronymic").setCaption("Отчество");
        grid.getColumn("specialization").setCaption("Специализация");

        editor.editorLabel.addComponent(statistics);
    }

    private void getStatistics() {
        if (editor.entityA != null) {

            Dialog dialog = new Dialog();
            dialog.setDialogWidth("95%");
            dialog.setDialogHeight("95%");

            Label label = new Label("Статистика");

            Grid<Recipe> dialogGrid = new Grid<>(Recipe.class);
            dialogGrid.setItems(recipeRepository.getByDoctor((Doctor) editor.entityA));
            dialogGrid.setWidth("100%");
            dialogGrid.setColumns("description", "patient", "doctor", "date", "expirationDate", "priority");

            dialog.addComponents(label, dialogGrid);
            dialog.open();
        }
    }
}