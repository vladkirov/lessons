package com.vladkirov.lessons.lesson07.homework01;

public class Teacher
        extends ManInSchool
        implements TeachAble {

    public Teacher(String name, int age, String subject) {
        super(name, age, subject);
    }

    @Override
    public void teach(Student student) {
        if (student != null && student.getSubject().equals(this.getSubject()))
            student.study();
    }
}
