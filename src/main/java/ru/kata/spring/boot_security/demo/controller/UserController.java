package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Возвращает информацию о пользователе по его ID.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
   }

   //Возвращает информацию о текущем аутентифицированном пользователе. пользователь запрашивает свои собственные данные
   @GetMapping("/getUserInfo")
    public ResponseEntity<User> getUserInfo (@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
   }

}