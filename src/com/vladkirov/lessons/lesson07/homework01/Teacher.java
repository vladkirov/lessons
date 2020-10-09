package com.vladkirov.lessons.lesson07.homework01;

public class Teacher
        extends Man
        implements TeachAble {

    public Teacher(String name, int age, String subject) {
        super(name, age, subject);
    }

    @Override
    public void teach(StudAble stud) {
        stud.study();
    }
}
