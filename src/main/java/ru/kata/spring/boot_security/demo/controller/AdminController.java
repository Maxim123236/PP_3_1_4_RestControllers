package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("principal", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/new")
    public String registrationPost(@ModelAttribute("newUser") User user, @AuthenticationPrincipal UserDetails userDetails) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/update/{id}")
    public String patchAdminRedactor(@ModelAttribute("user") User user, @PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.adminRedactor(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String adminDelete(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}