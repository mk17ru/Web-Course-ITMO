package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.LoginForm;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/api/1/users/auth")
    public User findUserByJwt(@RequestParam String jwt) {
        return jwtService.find(jwt);
    }


    @GetMapping("/api/1/users")
    public List<User> findUsers() {
        return userService.findAll();
    }

    @PostMapping("/api/1/users/register")
    public void registerUser(@RequestBody @Valid LoginForm userCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        userService.addUser(userCredentials.getLogin(), userCredentials.getName(), userCredentials.getPassword());
    }
}
