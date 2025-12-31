package com.simpleems;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class EventManager implements EventOperations {
    ArrayList<Event> events = new ArrayList<>();
    int nextId = 1;
    File file;

    public EventManager(String filename) {
        file = new File(filename);
    }

    public void addEvent(Event e) throws InvalidEventException {
        if (e.dateTime.isBefore(LocalDateTime.now()))
            throw new InvalidEventException("Event must be in the future");
        events.add(e);
        if (e.id >= nextId) nextId = e.id + 1;
    }

    public List<Event> listEvents() {
        ArrayList<Event> copy = new ArrayList<>(events);
        Collections.sort(copy, (a, b) -> a.dateTime.compareTo(b.dateTime));
        return copy;
    }

    public Event getById(int id) {
        for (Event e : events) {
            if (e.id == id) return e;
        }
        return null;
    }

    public void register(int id, Attendee a) throws InvalidEventException {
        Event e = getById(id);
        if (e == null) throw new InvalidEventException("Sorry.No such event");
        if (e.isFull()) throw new InvalidEventException("Event is full.Better Luck Next Time..");
        e.registerOne();
    }

    public void save() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Event e : events) {
            bw.write(e.toFileLine());
            bw.newLine();
        }
        bw.close();
    }

    public void load() throws Exception {
        events.clear();
        if (!file.exists()) return;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            Event e = Event.fromFileLine(line);
            events.add(e);
            if (e.id >= nextId) nextId = e.id + 1;
        }
        br.close();
    }

    public int getNextId() {
        return nextId++;
    }
}
