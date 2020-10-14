package com.vladkirov.lessons.examenation01;

import java.time.*;
import java.util.Objects;

abstract public class Abonement {
    protected LocalDateTime registrationBegin;
    protected LocalDateTime RegistrationEnd;
    protected Visitor visitor;

    public Abonement(LocalDateTime registrationBegin, LocalDateTime registrationEnd, Visitor visitor) {
        Objects.requireNonNull(registrationBegin);
        Objects.requireNonNull(registrationEnd);
        Objects.requireNonNull(visitor);

        if (!registrationEnd.isAfter(registrationBegin))
            throw new IllegalArgumentException("registrationEnd < registrationBegin");

        this.registrationBegin = registrationBegin;
        this.RegistrationEnd = registrationEnd;
        this.visitor = visitor;
    }
}
