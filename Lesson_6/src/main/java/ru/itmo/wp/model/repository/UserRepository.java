package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface UserRepository {
    User find(long id);
    User findByLogin(String login);
    User findByLoginAndPasswordSha(String login, String passwordSha);
    List<User> findAll();
    void save(User user, String passwordSha);
    User findByEmailAndPasswordSha(String mail, String passwordSha);
    User findByLoginOrEmailAndPasswordSha(String loginOrEmail, String passwordSha);
    int findCount();
}
