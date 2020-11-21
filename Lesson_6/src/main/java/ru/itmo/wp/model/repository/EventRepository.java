package ru.itmo.wp.model.repository;
import ru.itmo.wp.model.domain.Event;

import java.sql.SQLException;

public interface EventRepository {
    void save(Event event);
    Event find(long id);

}
