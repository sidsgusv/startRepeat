package org.example.start.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;

public class Beer {
    @Positive(message = "Id must be a positive value")
    private int id;

    @NotNull(message="Name can not be empty")
    @Size(min= 2, max = 20,message = "The name should be between 2 and 20 symbols")
    private String name;

    @Positive(message = "ABV must be a positive value")
    private double abv;

    private Style style;
    private LocalDate date;
    private User createdBy;


    public Beer() {
    }

    public Beer(int id, String name, double abv) {
        this.id = id;
        this.name = name;
        this.abv = abv;
    }

    public Beer(int id, String name, double abv, Style style, LocalDate date) {
        this.id = id;
        this.name = name;
        this.abv = abv;
        this.style = style;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Beer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Beer setName(String name) {
        this.name = name;
        return this;
    }

    public double getAbv() {
        return abv;
    }

    public Beer setAbv(double abv) {
        this.abv = abv;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Beer setStyle(Style style) {
        this.style = style;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Beer setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Beer setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}
