package ru.itmo.wp.form.validator;

import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.itmo.wp.form.DisableCredentials;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.service.UserService;

public class DisableCredentialsValidator {
    private final UserService userService;

    public DisableCredentialsValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserCredentials.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {

        }
    }
}
