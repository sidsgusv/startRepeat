package org.example.start.service;

import org.example.start.model.Style;

import java.util.List;

public interface StyleService {
    List<Style> getStyles();
    Style getStyleById(int id);
    Style createStyle(Style style);
    Style updateStyle(Style style);
    void deleteStyle(int id);

}
