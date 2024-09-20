package org.example.start.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BeerDTO {


    @NotNull(message="Name can not be empty")
    @Size(min= 2, max = 20,message = "The name should be between 2 and 20 symbols")
    private String name;

    @Positive(message = "ABV must be a positive value")
    private double abv;

    private int styleId;


    public BeerDTO() {
    }

    public BeerDTO(String name, double abv, int styleId) {
        this.name = name;
        this.abv = abv;
        this.styleId = styleId;
    }

    public @NotNull(message = "Name can not be empty") @Size(min = 2, max = 20, message = "The name should be between 2 and 20 symbols") String getName() {
        return name;
    }

    public BeerDTO setName(@NotNull(message = "Name can not be empty") @Size(min = 2, max = 20, message = "The name should be between 2 and 20 symbols") String name) {
        this.name = name;
        return this;
    }

    @Positive(message = "ABV must be a positive value")
    public double getAbv() {
        return abv;
    }

    public BeerDTO setAbv(@Positive(message = "ABV must be a positive value") double abv) {
        this.abv = abv;
        return this;
    }

    public int getStyleId() {
        return styleId;
    }

    public BeerDTO setStyleId(int styleId) {
        this.styleId = styleId;
        return this;
    }
}
