package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class TalksPage extends Page {
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("user") == null) {
            setMessage("Please, enter in your account!");
            throw new RedirectException("/index");
        }
        view.put("users", userService.findAll());
        createTalks(view);
        view.put("users", userService.findAll());
    }

    private void register(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        if (request.getParameter("user_option").equals("No user")) {
            setMessage("Select message recipient!");
            throw new RedirectException("/talks");
        }
        if (request.getParameter("typing").equals("")) {
            setMessage("Type something!");
            throw new RedirectException("/talks");
        }
        view.put("users", userService.findAll());
        User user = (User) request.getSession().getAttribute("user");
        Talk talk = new Talk();
        talk.setText(request.getParameter("typing"));
        talk.setSourceUserId(user.getId());
        talk.setTargetUserId(Long.parseLong(request.getParameter("user_option")));
        talkService.register(talk);
        createTalks(view);
    }

    private void createTalks(Map<String, Object> view) {
        List<Talk> talks = talkService.findAllByUserId(getUser().getId());
        for (Talk t : talks) {
            if (t.getSourceUserId() == 0) {
                t.setSourceUserId(getUser().getId());
            }
            if (t.getTargetUserId() == 0) {
                t.setTargetUserId(getUser().getId());
            }
            t.setSourceUserLogin(userService.find(t.getSourceUserId()).getLogin());
            t.setTargetUserLogin(userService.find(t.getTargetUserId()).getLogin());
        }
        view.put("talks", talks);
    }
}
