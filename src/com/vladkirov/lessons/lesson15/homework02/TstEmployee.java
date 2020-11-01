package com.vladkirov.lessons.lesson15.homework02;

import java.util.List;

public class TstEmployee {
    public static void main(String[] args) {
        List<Employee> employees = Employee.employeeGenerator(15);
        System.out.println("<<<<<<<<<<<< Sorting >>>>>>>>>>>>>");
        System.out.println("******** By Name *************");
        System.out.println(employees);
        employees.sort(new NameComparator());
        System.out.println(employees);

        System.out.println("******** By Name and salary *************");
        System.out.println(employees);
        employees.sort(new NameComparator().thenComparing(new SalaryComparator()));
        System.out.println(employees);

        System.out.println("******** By Name, salary, age, company *************");
        System.out.println(employees);
        employees.sort(new NameComparator().
                thenComparing(new SalaryComparator().
                        thenComparing(new AgeComparator().
                                thenComparing(new CompanyComparator()))));
        System.out.println(employees);
    }
}
