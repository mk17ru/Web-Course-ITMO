package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface ArticleRepository {
    Article find(long id);
    List<Article> findAllByUserId(long userId);
    void save(Article article);
    List<Article> findAll();
    Article findByUserId(long userId);
    void swapVisibility(Article article, String status);

}