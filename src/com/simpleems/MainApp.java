package com.simpleems;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainApp {
    static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EventManager manager = new EventManager("events.txt");

        try { manager.load(); 
        } catch (Exception e) {
        }

        ReminderThread rt = new ReminderThread(manager);
        rt.start();

        while (true) {
            System.out.println("\n1) Create Event");
            System.out.println("2) List Events");
            System.out.println("3) Register");
            System.out.println("4) Save & Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine();

            try {
                if (c.equals("1")) {
                    System.out.print("Organizer name: ");
                    String on = sc.nextLine();
                    System.out.print("Email: ");
                    String oe = sc.nextLine();
                    Organizer org = new Organizer(on, oe);

                    System.out.print("Title: ");
                    String t = sc.nextLine();

                    System.out.print("Date (yyyy-MM-dd HH:mm): ");
                    LocalDateTime dt = LocalDateTime.parse(sc.nextLine(), FMT);

                    System.out.print("Capacity: ");
                    int cap = Integer.parseInt(sc.nextLine());

                    Event e = org.create(manager.getNextId(), t, dt, cap);
                    manager.addEvent(e);
                    System.out.println("Event created.");
                }
                else if (c.equals("2")) {
                    for (Event e : manager.listEvents()) System.out.println(e);
                }
                else if (c.equals("3")) {
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    System.out.print("Email: ");
                    String em = sc.nextLine();
                    Attendee a = new Attendee(n, em);

                    System.out.print("Event ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    manager.register(id, a);
                    System.out.println("Registered.");
                }
                else if (c.equals("4")) {
                    manager.save();
                    System.out.println("Saved!!!");
                    break;
                }
                else {
                    System.out.println("Invalid option.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        sc.close(); 
    }
}
