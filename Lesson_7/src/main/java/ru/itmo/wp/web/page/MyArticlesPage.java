package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.annotation.Json;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage extends Page{
    private final ArticleService articleService = new ArticleService();


    protected void action(HttpServletRequest request, Map<String, Object> view) {
        checkUser(request, (User)request.getSession().getAttribute("user"));
        view.put("myArticles", articleService.findAllByUserId(((User)request.getSession().getAttribute("user")).getId()));
    }

    @Json
    private void swapVision(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Article article;
        try {
            article = articleService.find(Long.parseLong(request.getParameter("articleId")));
        } catch (NumberFormatException e){
            throw new ValidationException("Wrong request!");
        }
        if (request.getParameter("status") == null) {
            throw new ValidationException("Wrong request!");
        }
        checkUser(request, getUser(request));
        articleService.validate(article);
        if (getUser(request).getId() != article.getUserId()) {
            request.getSession().setAttribute("message", "You are hacker!");
            throw new RedirectException("/index");
        }
        articleService.swapVisibility(article, request.getParameter("status"));
        view.put("hidden", request.getParameter("status").equals("HIDE") ? "SHOW" : "HIDE");
    }

}
