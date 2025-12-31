package com.simpleems;

import java.util.List;

public interface EventOperations {
    void addEvent(Event e) throws InvalidEventException;
    List<Event> listEvents();
    Event getById(int id);
    void register(int id, Attendee a) throws InvalidEventException;
    void save() throws Exception;
    void load() throws Exception;
}
