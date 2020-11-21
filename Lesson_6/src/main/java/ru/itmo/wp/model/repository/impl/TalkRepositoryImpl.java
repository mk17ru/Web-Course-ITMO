package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.TalkRepository;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class TalkRepositoryImpl extends BasicRepositoryImpl<Talk> implements TalkRepository {

    @Override
    public Talk find(long id) {
        return super.find(id);
    }

    @Override
    public List<Talk> findAllByUserId(long userId) {
        return super.findAllBy(userId, "sourceUserId=? OR targetUserId=?");
    }

    @Override
    public void save(Talk talk) {
        super.save(talk, "INSERT INTO `Talks` (`sourceUserId`, `targetUserId`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())", null);
    }

    @Override
    protected void generatedKeys(ResultSet generatedKeys, Talk talk) throws SQLException {
        talk.setId(generatedKeys.getLong(1));
        talk.setCreationTime(find(talk.getId()).getCreationTime());
    }

    @Override
    protected String getName() {
        return "Talks";
    }

    @Override
    public Talk toObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    talk.setId(resultSet.getLong(i));
                    break;
                case "targetUserId":
                    talk.setTargetUserId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    talk.setSourceUserId(resultSet.getLong(i));
                    break;
                case "text":
                    talk.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    talk.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }
        return talk;
    }

    @Override
    protected void setStatementKeys(PreparedStatement statement, Talk talk, List<Object> l) throws SQLException {
        super.applyStatement(statement, Arrays.asList(talk.getSourceUserId(), talk.getTargetUserId(), talk.getText()));
    }
}
