package org.example.start.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Style {
    @Positive
    private int id;
    @Size(min=2, max=35,message = "Style name should be between 2 and 35 symbols.")
    private String name;


    public Style() {
    }
    public Style(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Style setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Style setName(String name) {
        this.name = name;
        return this;
    }
}
