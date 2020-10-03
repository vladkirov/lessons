package Lesson02;

import java.util.Scanner;

public class HomeWork05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int inputNumber = scanner.nextInt();

        switch (inputNumber) {
            case 111, 222, 333 -> System.out.print("You'll get a book.");
            case 444, 555 -> System.out.print("You'll get a phone.");
            case 777 -> System.out.print("You'll get a notebook.");
            default -> System.out.print("You'll get nothing. ");
        }
    }
}
