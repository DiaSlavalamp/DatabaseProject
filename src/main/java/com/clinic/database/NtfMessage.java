package com.clinic.database;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public class NtfMessage extends Notification {
    public NtfMessage(String caption, String style) {
        super(caption);
        setStyleName(style);
        setDelayMsec(2000);
        setPosition(Position.BOTTOM_LEFT);
        show(Page.getCurrent());
    }
}
