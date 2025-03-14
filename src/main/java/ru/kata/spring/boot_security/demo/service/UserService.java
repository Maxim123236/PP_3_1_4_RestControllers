package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUsername(String username);

    User getUserById(Long id);

    void saveUser(User user);

    void updateUser(User updatedUser);

    void deleteUserById(long userId);
}