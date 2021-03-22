package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

import javax.validation.constraints.Pattern;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public String user(Model model, @Pattern(regexp = "[0-9]+", message = "Expected numbers") @PathVariable String id) {
        User user = null;
        try {
           user = userService.findById(Long.parseLong(id));
        } catch (NumberFormatException ignored) {}
        if (user != null) {
            model.addAttribute("curUser", user);
        }
        return "UserPage";
    }
}