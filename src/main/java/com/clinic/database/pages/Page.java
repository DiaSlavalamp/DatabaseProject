package com.clinic.database.pages;

import com.clinic.database.editors.Editor;
import com.clinic.database.entitys.EntityA;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.data.repository.CrudRepository;
import java.util.Collection;


public abstract class Page<T> extends VerticalLayout {

    protected CrudRepository repository;
    protected Editor editor;

    protected Grid<? extends EntityA> grid;

    public Page(Class<? extends EntityA> clazz, CrudRepository repository, Editor editor) {

        this.repository = repository;
        this.editor = editor;
        editor.setChangeHandler(Page.this::gridUpdate);
        
        grid = new Grid<>(clazz);
        grid.asSingleSelect().addValueChangeListener(e -> editor.selectEntityA(e.getValue()));
        grid.setWidth("100%");
        grid.setHeight("100%");
        grid.setHeightMode(HeightMode.CSS);
        gridUpdate();

        addComponents(editor.editorLabel, grid);
        setExpandRatio(grid, 1);//приоритет занимаемого пространства таблицы
        setStyleName("gridPage");
        setHeight("100%");

    }

    private void gridUpdate() {
        Collection c = (Collection<EntityA>) repository.findAll();
        grid.setItems(c);
    }

}
