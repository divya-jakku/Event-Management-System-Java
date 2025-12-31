package com.simpleems;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    int id;
    String title;
    LocalDateTime dateTime;
    int capacity;
    int registered;

    static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(int id, String title, LocalDateTime dt, int capacity) {
        this.id = id;
        this.title = title;
        this.dateTime = dt;
        this.capacity = capacity;
        this.registered = 0;
    }

    public Event(int id, String title, LocalDateTime dt, int cap, int reg) {
        this(id, title, dt, cap);
        this.registered = reg;
    }

    public boolean isFull() {
        return registered >= capacity;
    }

    public void registerOne() {
        registered++;
    }

    public String toFileLine() {
        return id + "|" + title + "|" + dateTime.format(FMT) + "|" + capacity + "|" + registered;
    }

    public static Event fromFileLine(String line) {
        String[] p = line.split("\\|");
        return new Event(
                Integer.parseInt(p[0]),
                p[1],
                LocalDateTime.parse(p[2], FMT),
                Integer.parseInt(p[3]),
                Integer.parseInt(p[4])
        );
    }

    public String toString() {
        return "ID:" + id + " | " + title + " | " + dateTime.format(FMT) +
               " | Cap:" + capacity + " | Reg:" + registered;
    }
}
