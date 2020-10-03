package Lesson02;

import java.util.Scanner;

public class HomeWork01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int answer;

        int minRange = 1;
        int maxRange = 100;
        int currentNumber;

        for (;;) {
            currentNumber = (maxRange + minRange) / 2;
            System.out.println("Is number equal " + currentNumber + "?");
            answer = scanner.nextInt();
            if (answer == 1) {
                System.out.println("Number is found.");
                break;
            } else {
                System.out.println("Is number greater than " + currentNumber + "?");
                answer = scanner.nextInt();
                if (answer == 1) minRange = currentNumber + 1;
                else maxRange = currentNumber - 1;
            }
        }
    }
}
