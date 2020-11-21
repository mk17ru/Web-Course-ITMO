package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    protected final UserService userService = new UserService();
    protected final EventService eventService = new EventService();
    protected final TalkService talkService = new TalkService();

    HttpServletRequest request;

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    void before(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
        view.put("userCount", Integer.toString(userService.findCount()));
        this.request = request;
    }

    void after(HttpServletRequest request, Map<String, Object> view) {

    }

    void setMessage(String message) {
        this.request.getSession().setAttribute("message", message);
    }

    void setUser(User user) {
        this.request.getSession().setAttribute("user", user);
    }

    User getUser() {
        return (User) this.request.getSession().getAttribute("user");
    }
}
