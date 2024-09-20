package org.example.start.repository;

import org.example.start.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();
    User getById(int id);
    User getByUsername(String username);

}
