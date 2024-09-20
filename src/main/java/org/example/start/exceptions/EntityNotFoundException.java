package org.example.start.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String type, int id) {
        this(type,"id", String.valueOf(id));

    }

    public EntityNotFoundException(String type, String attribute, String value) {
        super(String.format("%s with %s %s is not found",type,attribute,value));
    }
}
