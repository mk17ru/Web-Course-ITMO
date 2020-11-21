package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserRepositoryImpl extends BasicRepositoryImpl<User> implements UserRepository {
    @Override
    public User find(long id) {
        return super.find(id);
    }

    @Override
    public User findByLogin(String login) {
        return super.findBy(Collections.singletonList(login), "SELECT * FROM User WHERE login=?");
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        return super.findBy(Arrays.asList(login, passwordSha), "SELECT * FROM User WHERE login=? AND passwordSha=?");
    }

    @Override
    public User findByEmailAndPasswordSha(String email, String passwordSha) {
        return super.findBy(Arrays.asList(email, passwordSha), "SELECT * FROM User WHERE email=? AND passwordSha=?");
    }

    public User findByLoginOrEmailAndPasswordSha(String loginOrEmail, String passwordSha) {
        User user = findByLoginAndPasswordSha(loginOrEmail, passwordSha);
        if (user == null) {
            return findByEmailAndPasswordSha(loginOrEmail, passwordSha);
        } else {
            return user;
        }
    }

    @Override
    public int findCount() {
        return findAll().size();
    }

    public User toObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }

    @Override
    public void save(User user, String passwordSha) {
        List<Object> l = new ArrayList<>(); l.add(passwordSha);
        super.save(user, "INSERT INTO `User` (`login`, `passwordSha`, `email`, `creationTime`) VALUES (?, ?, ?, NOW())", l);
    }

    @Override
    protected void generatedKeys(ResultSet generatedKeys, User user) throws SQLException {
        user.setId(generatedKeys.getLong(1));
        user.setCreationTime(find(user.getId()).getCreationTime());
    }

    @Override
    protected void setStatementKeys(PreparedStatement statement, User user, List<Object> l) throws SQLException {
        super.applyStatement(statement, Arrays.asList(user.getLogin(), (String)l.iterator().next(), user.getEmail()));
    }

    @Override
    protected String getName() {
        return "User";
    }

    @Override
    public List<User> findAll() {
        return super.findAll("SELECT * FROM User ORDER BY id DESC", null);
    }
}
