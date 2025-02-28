package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User findByUsername(String username);
    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUser(Long id);

    void adminRedactor(User user, Long id);
}