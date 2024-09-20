package org.example.start.repository;

import org.example.start.model.Style;

import java.util.List;

public interface StyleRepository {
    List<Style> getStyles();
    Style getStyleById(int id);
    Style getStyleByName(String name);
    Style createStyle(Style style);
    Style updateStyle(Style style);
    void deleteStyle(int id);
}
