package com.vladkirov.lessons.examenation01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Logger {
    public static void println(String message) {
        System.out.println(message);
    }

    public static void printInviteInfo(Subscription subscription, Zone zone, LocalDateTime dateTimeInvite) {
        Objects.requireNonNull(subscription);
        Objects.requireNonNull(dateTimeInvite);

        println("<<<<<<  New visitor <<<<<<<");
        println("Last name: " + subscription.getVisitor().getLastName());
        println("First name: " + subscription.getVisitor().getFirstName());
        println("Invited zone: " + zone.name());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy в HH:mm");
        println("Date and time visit: " + formatter.format(dateTimeInvite));
        println(">>>>>>>>>>>>>");
    }

    public static void printPerson(Visitor visitor) {
       println("Name: " + visitor.getFirstName() + " " + visitor.getLastName());
    }
}
