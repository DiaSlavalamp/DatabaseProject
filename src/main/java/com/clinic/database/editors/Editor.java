package com.clinic.database.editors;

import com.clinic.database.Dialog;
import com.clinic.database.NtfMessage;
import com.clinic.database.entitys.EntityA;
import com.clinic.database.entitys.exeptions.BeforeCreationDateException;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.springframework.data.repository.CrudRepository;

public abstract class Editor<T> extends VerticalLayout {

    public EntityA entityA;
    protected CrudRepository repository;
    public HorizontalLayout editorLabel;
    protected HorizontalLayout dialogButtons;
    protected ChangeHandler changeHandler;
    protected Binder<EntityA> binder;
    protected Dialog dialog;

    protected Editor(Class<T> clazz) {

        dialog = new Dialog();

        Button newBtn = new Button("Добавить");
        newBtn.setIcon(VaadinIcons.PLUS);
        newBtn.addClickListener(e -> {
            try {
                newEntityA((EntityA) clazz.getDeclaredConstructor().newInstance());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Button changeBtn = new Button("изменить");
        changeBtn.addClickListener(e -> editEntityA());
        Button deleteBtn = new Button("Удалить");
        deleteBtn.setStyleName("redBtn");
        deleteBtn.setIcon(VaadinIcons.TRASH);
        deleteBtn.addClickListener(e -> delete());

        editorLabel = new HorizontalLayout();
        editorLabel.addComponent(newBtn);
        editorLabel.addComponent(changeBtn);
        editorLabel.addComponent(deleteBtn);

        Button save = new Button("ОК");
        save.addClickListener(e -> save());
        save.setIcon(VaadinIcons.CHECK);
        save.setStyleName("greenBtn");
        Button cancel = new Button("Отменить");
        cancel.addClickListener(e -> cancel());
        cancel.setStyleName("redBtn");

        dialogButtons = new HorizontalLayout(save, cancel);

    }

    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

    public void selectEntityA(EntityA c) {
        this.entityA = c;
    }

    private void newEntityA(EntityA c) {
        dialog.open();
        this.entityA = c;
        binder.setBean(entityA);
    }

    private void editEntityA() {
        if (entityA != null) {
            dialog.open();
            binder.setBean(entityA);
        }
    }

    private void delete() {
        try {
            repository.delete(entityA);
            changeHandler.onChange();
            new NtfMessage("Очищено!", "green");
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            e.printStackTrace();
            new NtfMessage("Нарушение целостности данных!", "red");
        } catch (Exception e) {
            e.printStackTrace();
            new NtfMessage("Ошибка!", "red");
        }
    }

    private void save() {
        if (entityA.haveNullFields()) {
            new NtfMessage("Есть незаполенные поля!", "red");
        } else {
            try {
                entityA.validate();
                repository.save(entityA);
                changeHandler.onChange();
                dialog.close();
                new NtfMessage("Сохранено!", "green");
            } catch (BeforeCreationDateException e) {
                e.printStackTrace();
                new NtfMessage("Дата создания не может быть после даты окончания действия!", "red");
            } catch (Exception e) {
                e.printStackTrace();
                new NtfMessage("Ошибка!", "red");
            }
        }
    }

    private void cancel() {
        this.entityA = null;
        dialog.close();
    }

}
