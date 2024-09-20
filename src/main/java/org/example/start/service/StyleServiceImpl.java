package org.example.start.service;

import org.example.start.exceptions.DuplicateEntityException;
import org.example.start.exceptions.EntityNotFoundException;
import org.example.start.model.Style;
import org.example.start.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public List<Style> getStyles() {
        return styleRepository.getStyles();
    }

    @Override
    public Style getStyleById(int id) {
        return styleRepository.getStyleById(id);
    }

    @Override
    public Style createStyle(Style style) {
        //Да няма две бири с еднакви Style

        boolean duplicateExist=true;

        try {
            styleRepository.getStyleByName(style.getName());
        } catch (EntityNotFoundException e) {
            duplicateExist = false;
        }

        if (duplicateExist) {
            throw new DuplicateEntityException("Style", "name", style.getName());
        }

        return styleRepository.createStyle(style);
    }

    @Override
    public Style updateStyle(Style style) {
        boolean duplicateExist=true;
   try {
       styleRepository.getStyleByName(style.getName());
   } catch (EntityNotFoundException e) {
       duplicateExist = false;
   }


        if (duplicateExist){
            throw new DuplicateEntityException("Style", "name", style.getName());
        }

        return styleRepository.updateStyle(style);
    }

    @Override
    public void deleteStyle(int id) {

        styleRepository.deleteStyle(id);

    }
}
