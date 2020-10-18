package com.vladkirov.lessons.examenation01;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Application {
    public static void main(String[] args) {
        Visitor ivan = new Visitor("Ivan", "Petrov", LocalDate.of(1986, 1, 15));
        Visitor petr = new Visitor("Petr", "Petrov", LocalDate.of(1996, 4, 15));
        Visitor elena = new Visitor("Elena", "Bok", LocalDate.of(1995, 4, 1));
        Visitor jack = new Visitor("Jack", "Smith", LocalDate.of(1965, 4, 15));
        Visitor ivanDouble = new Visitor("Ivan", "Petrov", LocalDate.of(1986, 1, 15));

        Subscription dayIvan = new Subscription(
                LocalDate.of(2020, 2, 3),
                LocalDate.of(2021, 2, 3),
                ivan,
                TypePass.DAY);

        Subscription dayIvanDouble = new Subscription(
                LocalDate.of(2020, 2, 3),
                LocalDate.of(2021, 2, 3),
                ivanDouble,
                TypePass.DAY);

        Subscription dayPetr = new Subscription(
                LocalDate.of(2019, 2, 3),
                LocalDate.of(2020, 2, 3),
                petr,
                TypePass.DAY);

        Subscription dayPetrNew = new Subscription(
                LocalDate.of(2020, 2, 3),
                LocalDate.of(2021, 6, 3),
                petr,
                TypePass.DAY);

        Subscription dayPetrCorrect = new Subscription(
                LocalDate.of(2020, 2, 3),
                LocalDate.of(2021, 2, 3),
                petr,
                TypePass.DAY);

        Subscription fullElena = new Subscription(
                LocalDate.of(2020, 5, 3),
                LocalDate.of(2021, 1, 3),
                elena,
                TypePass.FULL);

        Subscription singleJack = new Subscription(
                LocalDate.of(2020, 2, 3),
                LocalDate.of(2020, 11, 3),
                jack,
                TypePass.SINGLE);

        Subscription fullJack = new Subscription(
                LocalDate.of(2020, 2, 3),
                LocalDate.of(2021, 10, 3),
                jack,
                TypePass.FULL);

        Fitness fitness = new Fitness(2, LocalDateTime.of(2020, 10, 18, 10, 0));

        fitness.invite(dayIvan, Zone.GROUP_HALL);
        fitness.invite(dayPetr, Zone.GYM);
        fitness.invite(fullElena, Zone.SWIMMING_POOL);
        fitness.invite(singleJack, Zone.GROUP_HALL);
        fitness.invite(dayIvanDouble, Zone.GROUP_HALL);
        fitness.invite(dayIvanDouble, Zone.SWIMMING_POOL);
        fitness.invite(dayPetrCorrect, Zone.GROUP_HALL);
        fitness.invite(dayPetrNew, Zone.GROUP_HALL);

        fitness.listAllVisitors();

        fitness.setNowDateTime(LocalDateTime.of(2020, 10, 18, 23, 0));
        fitness.invite(fullJack, Zone.GROUP_HALL);

        fitness.close();
        fitness.listAllVisitors();
    }
}
