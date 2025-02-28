package ru.kata.spring.boot_security.demo.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
//        ((UserServiceImpl) userService).setPasswordEncoder(passwordEncoder);
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        User adminUser = new User("John", "Doe", 22, "johndoe_admin@gmail.com", "123", adminRoles);
        User user = new User("Bob", "Black", 33, "bob_black@gmail.com", "123", userRoles);
        userService.saveUser(adminUser);
        userService.saveUser(user);
    }
}
