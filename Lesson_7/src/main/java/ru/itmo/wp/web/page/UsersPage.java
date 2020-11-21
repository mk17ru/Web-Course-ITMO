package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.annotation.Json;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class UsersPage extends Page{

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    @Json
    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user", userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void findCurUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("curUser", userService.find(((User)request.getSession().getAttribute("user")).getId()));
    }
    @Json
    private void changeAdmin(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user;
        try {
            user = userService.find(Long.parseLong(request.getParameter("userId")));
        } catch (NumberFormatException e){
            throw new ValidationException("Wrong request!");
        }
        if (request.getParameter("status") == null) {
            throw new ValidationException("Wrong request!");
        }
        String admin = request.getParameter("status");
        checkUser(request, user);
        request.getSession().setAttribute("user", userService.find(getUser(request).getId()));
        if (getUser(request).isAdmin()) {
            userService.changeAdmin(user, request.getParameter("status"));
        } else {
            throw new ValidationException("You are not admin!");
        }

    }
}
