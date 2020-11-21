package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageServlet extends HttpServlet {
    private List<Message> messages = Collections.synchronizedList(new ArrayList<>());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String json = "";
        if ("/message/auth".equals(uri)) {
            String user = (String) request.getSession().getAttribute("user");
            if (user == null) {
                user = request.getParameter("user");
                if (user == null) {
                    user = "";
                } else {
                    request.getSession().setAttribute("user", user);
                }
            } else {
                request.getSession().setAttribute("user", user);
            }
            json = new Gson().toJson(user);
        } else if ("/message/findAll".equals(uri)) {
            json = new Gson().toJson(messages);
        } else if ("/message/add".equals(uri)){
            messages.add(new Message((String)request.getSession().getAttribute("user"), request.getParameter("text")));
        } else {
            throw new IllegalArgumentException();
        }
        response.getWriter().print(json);
        response.setContentType("application/json");
        response.getWriter().flush();

















    }
}
