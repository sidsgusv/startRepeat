package org.example.start.service;

import org.example.start.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(int id);
    User getByUsername(String username);
}
