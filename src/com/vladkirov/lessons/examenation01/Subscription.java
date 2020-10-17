package com.vladkirov.lessons.examenation01;

import java.time.*;
import java.util.Objects;

public class Subscription {
    private final LocalDate registrationBegin;
    private final LocalDate RegistrationEnd;
    private final Visitor visitor;
    private final TypePass typePass;

    public Subscription(LocalDate registrationBegin, LocalDate registrationEnd, Visitor visitor, TypePass typePass) {
        Objects.requireNonNull(registrationBegin);
        Objects.requireNonNull(registrationEnd);
        Objects.requireNonNull(visitor);

        if (registrationEnd.isBefore(registrationBegin))
            throw new IllegalArgumentException("registrationEnd < registrationBegin");

        this.registrationBegin = registrationBegin;
        this.RegistrationEnd = registrationEnd;
        this.visitor = visitor;
        this.typePass = typePass;
    }

    public TypePass getTypePass() {
        return typePass;
    }

    public LocalDate getRegistrationBegin() {
        return registrationBegin;
    }

    public LocalDate getRegistrationEnd() {
        return RegistrationEnd;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return registrationBegin.isEqual(that.registrationBegin) &&
                RegistrationEnd.isEqual(that.RegistrationEnd) &&
                visitor.equals(that.visitor) &&
                typePass == that.typePass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationBegin, RegistrationEnd, visitor, typePass);
    }
}
