package com.vladkirov.lessons.lesson15.homework02;

// Создать список объетов List<Employee> (использовать метод employeeGenerator)
// и сортировать по:
// имени
// имени и зарплате
// имени, зарплате, возрасту и компании

import java.util.*;

public class Employee {
    private String name;
    private String company;
    private int salary;
    private int age;

    // TODO: конструктор, геттеры и сеттеры


    public Employee(String name, String company, int salary, int age) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(company);

        if (salary <= 0 || age < 18)
            throw new IllegalArgumentException("salary and age must be correct");

        this.name = name;
        this.company = company;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public static List<Employee> employeeGenerator(int num) {
        if (num <= 0) return Collections.emptyList();

        Random random = new Random();

        // метод для создания списка объектов класса Employee
        String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda", "Elizabeth"}; // массив с именами
        String[] companies = {"Microsoft", "IBM", "Google", "General Electric", "Siemens", "Samsung", "Apple"}; // массив с названиями компаний

        List<Employee> employees = new ArrayList<>(num);

        // добавление num объектов Employee в список (employees)
        // TODO: объекты создавать с рандомными значениями. Возраст от 21 до 60 и не забудьте про зп
        for (int i = 0; i < num; i++) {
            employees.add(new Employee( names[random.nextInt(names.length)],
                                        companies[random.nextInt(companies.length)],
                                        1 + random.nextInt(100),
                                        21 + random.nextInt(39)));
        }
        return employees;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Character.compare(o1.getName().charAt(0), o2.getName().charAt(0));
    }
}

class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getSalary(), o2.getSalary());
    }
}

class AgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}

class CompanyComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Character.compare(o1.getCompany().charAt(0), o2.getCompany().charAt(0));
    }
}