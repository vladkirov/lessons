package com.vladkirov.lessons.lesson07.homework01;

public class Director
    extends Man
    implements Leader {
        public Director(String name, int age) {
            super(name, age);
        }

        @Override
        public boolean startLearning() {
            return true;
        }

        @Override
        public boolean stopLearning() {
            return false;
        }
    }
