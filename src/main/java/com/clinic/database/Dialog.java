package com.clinic.database;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Dialog extends VerticalLayout {

    private Window dialog;

    public Dialog() {
        dialog = new Window();
        dialog.center();
        dialog.setContent(this);
    }

    public void open() {
        UI.getCurrent().addWindow(dialog);
    }
    public void close(){
        dialog.close();
    }

    public void setDialogWidth(String s){
        dialog.setWidth(s);
    }

    public void setDialogHeight(String s){
        dialog.setHeight(s);
    }
}

