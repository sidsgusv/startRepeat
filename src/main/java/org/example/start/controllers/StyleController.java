package org.example.start.controllers;

import jakarta.validation.Valid;
import org.example.start.exceptions.DuplicateEntityException;
import org.example.start.exceptions.EntityNotFoundException;
import org.example.start.exceptions.UnauthorizedOperationException;
import org.example.start.model.Beer;
import org.example.start.model.Style;
import org.example.start.model.User;
import org.example.start.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.List;

@RestController
@RequestMapping("/api/styles")
public class StyleController {
    private final StyleService styleService;

    @Autowired
    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @GetMapping
    public List<Style> getAllStyles() {
        return styleService.getStyles();
    }

    @GetMapping("/{id}")
    public Style getStyleById(@PathVariable int id) {
        try {
            return styleService.getStyleById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Style createStyle(@Valid @RequestBody Style style) {
        try {
            return styleService.createStyle(style);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public Style updateStyle(@PathVariable int id, @Valid @RequestBody Style style) {
        try {
            return styleService.updateStyle(style);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public void deleteStyle(@PathVariable int id) {
        try {

            styleService.deleteStyle(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorizedOperationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
