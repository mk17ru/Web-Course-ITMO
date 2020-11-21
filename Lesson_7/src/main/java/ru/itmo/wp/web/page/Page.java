package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;

public class Page {
    protected final UserService userService = new UserService();

    protected void checkUser(HttpServletRequest request, User user) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "Please, enter in your account!");
            throw new RedirectException("/index");
        }
        if (user == null) {
            request.getSession().setAttribute("message", "No user!");
            throw new RedirectException("/index");
        }
    }

    protected User getUser(HttpServletRequest request) {
        return (User)request.getSession().getAttribute("user");
    }
}
