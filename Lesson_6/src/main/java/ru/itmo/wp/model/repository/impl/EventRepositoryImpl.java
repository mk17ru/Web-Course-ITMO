package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.repository.EventRepository;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class EventRepositoryImpl extends BasicRepositoryImpl<Event> implements EventRepository {

    @Override
    protected String getName() {
        return "Event";
    }

    @Override
    public Event find(long id) {
        return super.find(id);
    }

    @Override
    public void save(Event event) {
        super.save(event, "INSERT INTO `Event` (`userId`, `type`, `creationTime`) VALUES (?, ?, NOW())", null);
    }

    @Override
    protected void generatedKeys(ResultSet generatedKeys, Event event) throws SQLException {
        event.setId(generatedKeys.getLong(1));
        event.setCreationTime(find(event.getId()).getCreationTime());
    }

    public Event toObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        Event event = new Event();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    event.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    event.setUserId(resultSet.getLong(i));
                    break;
                case "type":
                    event.setType(Enum.valueOf(Event.Type.class, resultSet.getString(i)));
                    break;
                case "creationTime":
                    event.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }
        return event;
    }

    @Override
    protected void setStatementKeys(PreparedStatement statement, Event event, List<Object> l) throws SQLException {
        super.applyStatement(statement, Arrays.asList(event.getUserId(), event.getType().name()));
    }

}
