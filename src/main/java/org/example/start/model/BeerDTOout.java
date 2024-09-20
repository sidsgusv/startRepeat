package org.example.start.model;

public class BeerDTOout {
    private String name;
    private double abv;


    public BeerDTOout() {
    }

    public BeerDTOout(String name, double abv) {
        this.name = name;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }

    public BeerDTOout setName(String name) {
        this.name = name;
        return this;
    }

    public double getAbv() {
        return abv;
    }

    public BeerDTOout setAbv(double abv) {
        this.abv = abv;
        return this;
    }
}
