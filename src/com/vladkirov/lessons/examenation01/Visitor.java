package com.vladkirov.lessons.examenation01;

import java.time.*;
import java.util.Objects;

public class Visitor {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDay;

    public Visitor(String firstName, String lastName, LocalDate birthDay) {
        this.firstName = Objects.requireNonNull(firstName, "firstName is null");
        this.lastName  = Objects.requireNonNull(lastName, "lastName is null");
        this.birthDay  = Objects.requireNonNull(birthDay, "birthDay is null");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
