package org.example.start.exceptions;

public class DuplicateEntityException extends RuntimeException{

    public DuplicateEntityException(String type, String attribute, String value) {
        super(String.format("%s with %s %s already exist.", type, attribute, value));
    }


}
