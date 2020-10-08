package com.vladkirov.lessons.lesson07.homework01;

abstract public class ManInSchool extends Man {
    protected String subject;

    public ManInSchool(String name, int age, String subject) {
        super(name, age);

        if (subject == null || subject.trim().length() < 2)
            throw new IllegalArgumentException("Subject too short.");

        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
