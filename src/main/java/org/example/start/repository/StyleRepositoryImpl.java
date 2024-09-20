package org.example.start.repository;

import org.example.start.exceptions.EntityNotFoundException;
import org.example.start.model.Beer;
import org.example.start.model.Style;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StyleRepositoryImpl implements StyleRepository {

    private final List<Style> styles;

    public StyleRepositoryImpl() {
        this.styles = new ArrayList<>();
        styles.add(new Style(1,"Black"));
        styles.add(new Style(2, "Green"));
        styles.add(new Style(3, "Yelow"));
        styles.add(new Style(4, "Blonde"));
    }

    @Override
    public List<Style> getStyles() {
        return new ArrayList<>(styles);//inache vrasha priginalnata kolektzija
    }

    @Override
    public Style getStyleById(int id) {
        return styles.stream()
                .filter(style -> style.getId() == id)
                .findFirst()
                .orElseThrow(()->new EntityNotFoundException("Style",id));
    }

    @Override
    public Style getStyleByName(String name) {
        return styles.stream()
                .filter(style -> style.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Style","name",name));
    }

    @Override
    public Style createStyle(Style style) {
        styles.add(style);
        return style;
    }

    @Override
    public Style updateStyle(Style style) {
       Style styleToUpdate = getStyleById(style.getId());
       styleToUpdate.setName(style.getName());

        return styleToUpdate;
    }

    @Override
    public void deleteStyle(int id) {
        Style styleToDelete = getStyleById(id);
        styles.remove(styleToDelete);

    }
}
