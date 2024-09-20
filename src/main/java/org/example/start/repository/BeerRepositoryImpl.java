package org.example.start.repository;

import org.example.start.exceptions.EntityNotFoundException;
import org.example.start.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BeerRepositoryImpl implements BeerRepository {

    private final List<Beer> beers;
    private final StyleRepository styleRepository;
    private static int id = 1;

    @Autowired
    public BeerRepositoryImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
        beers = new ArrayList<>();

        beers.add(new Beer(id++, "Zirst", 5.0, styleRepository.getStyleById(3), LocalDate.now()));
        beers.add(new Beer(id++, "Second", 2.0, styleRepository.getStyleById(1), LocalDate.now()));
        beers.add(new Beer(id++, "Third", 2.70, styleRepository.getStyleById(2), LocalDate.now()));
        beers.add(new Beer(id++, "Third", 2.70, styleRepository.getStyleById(3), LocalDate.now()));
    }

    @Override
    public List<Beer> getAll(String name, String styleName, String sortBy, String sortOrder) {
        List<Beer> result = new ArrayList<>(beers);
        result = filterByName(result, name);
        result = filterByStyleName(result, styleName);
        result = sortBy(result, sortBy);
       sortOrder(result,sortOrder);
        return result;
    }

    private void sortOrder(List<Beer> result, String sortOrder) {
        if (result.isEmpty() || sortOrder == null || sortOrder.equalsIgnoreCase("asc")) {
            return;
        }

        if (sortOrder.equalsIgnoreCase("desc")) {
        Collections.reverse(result);
}
    }

    private List<Beer> filterByStyleName(List<Beer> result, String name) {
        if (result.isEmpty() || name == null) {
            return result;
        }

        return result.stream()
                .filter(beer -> containsIgnoreCase(beer.getStyle().getName(), name))
                .collect(Collectors.toList());
    }

    private List<Beer> filterByName(List<Beer> result, String name) {
        if (result.isEmpty() || name == null) {
            return result;
        }
        return result.stream()
                .filter(beer -> containsIgnoreCase(beer.getName(), name))
                .collect(Collectors.toList());
    }

    private boolean containsIgnoreCase(String value, String target) {
        return value.toLowerCase(Locale.ROOT).contains(target.toLowerCase(Locale.ROOT));
    }

    private List<Beer> sortBy (List<Beer> result, String sortBy) {
        if (result.isEmpty() || sortBy == null) {
            return result;
        }

        switch (sortBy) {
            case "beerName":
                result.sort(Comparator.comparing(Beer::getName));
                break;

            case "style":
                result.sort(Comparator.comparing(beer -> beer.getStyle().getName()));
                break;


        }


        return result;
    }


    @Override
    public Beer getById(int id) {
        return beers
                .stream()
                .filter(beer -> beer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }


    @Override
    public Beer getByName(String name) {
        return beers
                .stream()
                .filter(beer -> beer.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    @Override
    public void create(Beer beer) {
        beers.add(beer);
    }

    @Override
    public void update(Beer beer) {
        Beer beerToUpdate = getById(beer.getId());
        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    @Override
    public void delete(int id) {
        Beer beerToDelete = getById(id);//metoda ot gore
        beers.remove(beerToDelete);
    }

    @Override
    public int getNextBeerId() {
        return id++;
    }
}
