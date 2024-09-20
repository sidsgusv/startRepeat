package org.example.start.service;

import org.example.start.model.Beer;
import org.example.start.model.User;

import java.util.List;

public interface BeerService {
    List<Beer> getAll(String name, String styleName, String sortBy, String sortOrder);

    Beer getById(int id);

    void create(Beer beer,User user);

    void update(Beer beer, User user);

    void delete(int id, User user);



    int getNextBeerId();


}
