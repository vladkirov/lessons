package com.vladkirov.lessons.lesson07.homework01;

public class Student
        extends Man
        implements StudAble {
    private int levelKnowledge;

    public Student(String name, int age, String subject, int levelKnowledge) {
        super(name, age, subject);
        if (levelKnowledge < 0)
            throw new IllegalArgumentException("Level knowledge of a student < 0.");
        this.subject = subject;
        this.levelKnowledge = levelKnowledge;
    }

    @Override
    public void study() {
        this.levelKnowledge++;
    }
}
