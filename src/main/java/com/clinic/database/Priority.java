package com.clinic.database;

public enum Priority {NORMAL("Нормальный"), CITO("Срочно"), STATIM("Немедленно");

    private String name;

    Priority(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
