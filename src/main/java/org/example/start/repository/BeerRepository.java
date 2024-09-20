package org.example.start.repository;

import org.example.start.model.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> getAll(String name, String styleName, String sortBy, String sortOrder);

    Beer getById(int id);

    Beer getByName(String name);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);

    int getNextBeerId();


}
