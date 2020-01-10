package com.clinic.database.entitys;

public abstract class EntityA {
    protected String format(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        } else {
            return text.substring(0, 1).toUpperCase() + text.substring(1);
        }
    }

    public abstract boolean haveNullFields();

    public abstract void validate() throws Exception;
}
