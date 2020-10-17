package com.vladkirov.lessons.examenation01;

import java.time.*;
import java.util.Objects;

public class Visitor {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDay;

    public Visitor(String firstName, String lastName, LocalDate birthDay) {
        Objects.requireNonNull(firstName, "firstName is null");
        Objects.requireNonNull(lastName, "lastName is null");
        Objects.requireNonNull(birthDay, "birthDay is null");

        if (firstName.trim().length() < 2 || lastName.trim().length() < 2)
            throw new IllegalArgumentException("Incorrect visitor name");
        this.firstName = firstName;
        this.lastName  = lastName;
        this.birthDay  = birthDay;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return firstName.equals(visitor.firstName) &&
                lastName.equals(visitor.lastName) &&
                birthDay.isEqual(visitor.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDay);
    }
}
