package com.vladkirov.lessons.examenation01;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        Visitor ivan = new Visitor("Ivan", "Petrov", LocalDate.of(1986,01,15));
    }
}
