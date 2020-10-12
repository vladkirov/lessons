package com.vladkirov.lessons.lesson07.homework01;

public class School {
    private String name;
    private Director director;
    private Teacher[] teachers;
    private StudAble[] students;
    private int currentStudents;
    private int currentTeachers;
    private boolean isWork;

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    public School(String name, Director director, int amountTeachers, int amountStudents) {
        if (name == null || name.trim().length() < 2)
            throw new IllegalArgumentException("Name school is too short.");
        this.name = name;
        this.director = director;
        teachers = new Teacher[amountTeachers];
        students = new Student[amountStudents];
    }

    public void addTeacher(Teacher teacher) {
        if (teacher == null)
            throw new IllegalArgumentException("teacher == null");
        if (currentTeachers < teachers.length)
            teachers[currentTeachers++] = teacher;
    }

    public void addStudent(StudAble student) {
        if (student == null)
            throw new IllegalArgumentException("student == null");
        if (currentStudents < students.length)
            students[currentStudents++] = student;
    }

    public void BeginStudDay() {
        this.setWork(director.startLearning());
        studyAll();
        this.setWork(director.stopLearning());
    }

    private void studyAll() {
        if (!isWork()) System.out.println("Learn not started!");
        for (Teacher teacher : teachers)
            if (teacher != null)
                for (StudAble student : students)
                    if (student != null && student.getSubject().equalsIgnoreCase(teacher.getSubject()))
                        teacher.teach(student);
    }
}
