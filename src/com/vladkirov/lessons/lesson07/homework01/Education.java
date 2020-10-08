package com.vladkirov.lessons.lesson07.homework01;

public class Education {
    public static void main(String[] args) {
        Student studentVlad = new Student("Vlad", 7, "Math", 2);
        Student studentLena = new Student("Lena", 8, "Math", 0);
        Student studentIvan = new Student("Ivan", 9, "History", 3);

        Teacher teacherMariVanna = new Teacher("MariVanna", 45, "Math");
        Teacher teacherSanSan = new Teacher("SanSan", 35, "History");

        Director director = new Director("Ioan", 50);

        School school = new MiddleSchool("Middle", director, 2, 3);

        school.addStudent(studentIvan);
        school.addStudent(studentLena);
        school.addStudent(studentVlad);

        school.addTeacher(teacherMariVanna);
        school.addTeacher(teacherSanSan);

        school.BeginStudDay();
    }
}
