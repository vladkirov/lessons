package com.vladkirov.lessons.lesson07.homework01;

abstract public class Man {
    protected final String name;
    protected int age;
    protected String subject;

    public Man(String name, int age, String subject) {
        if (age < 6)
            throw new IllegalArgumentException("Age of a man < 6");
        if (name == null || name.trim().length() < 2)
            throw new IllegalArgumentException("Name of a man too short.");
        this.name = name;
        this.age = age;

        if (subject == null || subject.trim().length() < 2)
            throw new IllegalArgumentException("Subject too short.");

        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

}
