package com.simpleems;

import java.time.LocalDateTime;
import java.time.Duration;

public class ReminderThread extends Thread {
    EventManager manager;
    boolean running = true;

    public ReminderThread(EventManager m) {
        manager = m;
        setDaemon(true);
    }

    public void run() {
        while (running) {
            try {
                for (Event e : manager.listEvents()) {
                    long mins = Duration.between(LocalDateTime.now(), e.dateTime).toMinutes();
                    if (mins >= 0 && mins <= 60) {
                        System.out.println("[REMINDER] Event \"" + e.title + "\" in " + mins + " minute(s).");
                    }
                }
                Thread.sleep(60000);
            } catch (Exception ex) {}
        }
    }
}

