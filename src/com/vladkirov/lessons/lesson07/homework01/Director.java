package com.vladkirov.lessons.lesson07.homework01;

final public class Director
        extends Man
        implements StudAble {
    private int level;

    public Director(String name, int age) {

        super(name, age, "Math");
    }

    public boolean startLearning() {
        return true;
    }

    public boolean stopLearning() {
        return false;
    }


    @Override
    public void study() {
        this.level += 2;
    }
}
