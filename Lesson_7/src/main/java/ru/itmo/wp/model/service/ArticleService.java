package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void add(Article article) {
        articleRepository.save(article);
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public List<Article> findAllByUserId(long userId) {
        return articleRepository.findAllByUserId(userId);
    }


    public void validate(Article article) throws ValidationException {
        if (article == null) {
            throw new ValidationException("No article");
        }
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is required");
        }
        if (article.getTitle().length() > 254) {
            throw new ValidationException("Title can't be longer than 254 letters");
        }
        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Text is required");
        }
        if (article.getText().length() > 999) {
            throw new ValidationException("Text can't be longer than 999 letters");
        }
    }



    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void swapVisibility(Article article, String status) {
        articleRepository.swapVisibility(article, status);
    }


}