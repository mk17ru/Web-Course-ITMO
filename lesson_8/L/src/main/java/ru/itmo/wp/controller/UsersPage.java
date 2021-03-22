package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.DisableCredentials;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("disableForm", new UserCredentials());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String usersPost(@Valid @ModelAttribute("disableForm") DisableCredentials disableForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            putMessage(httpSession, "Errors!");
            return "redirect:/users/all";
        }
        User user;
        try {
            user = userService.findById(Long.parseLong(disableForm.getCurId()));
        } catch (NumberFormatException e) {
            putMessage(httpSession, "Wrong input!");
            return "redirect:/users/all";
        }

        if (getUser(httpSession) == null) {
            putMessage(httpSession, "You are not logged!");
            return "redirect:/";
        }

        if (user == null) {
            putMessage(httpSession, "Wrong input!");
            return "redirect:/users/all";
        }
        userService.changeStatus(user, disableForm.getDisabled());

        putMessage(httpSession, "Congrats, you have changed the status!");

        return "redirect:/users/all";
    }
}
