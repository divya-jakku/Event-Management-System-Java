package com.simpleems;

import java.time.LocalDateTime;

public class Organizer extends Person {
    public Organizer(String n, String e) {
        super(n, e);
    }

    public Event create(int id, String title, LocalDateTime dt, int cap) {
        return new Event(id, title, dt, cap);
    }
}

