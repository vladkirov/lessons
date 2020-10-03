package Lesson02;

import java.util.Scanner;

public class HomeWork06 {
    public static void main(String[] args) {
        int minRandom = 1;
        int maxRandom = 9;
        int diff = maxRandom - minRandom;
        int randomNumber = (int) (Math.random() * ++diff) + minRandom;

        Scanner scanner = new Scanner(System.in);
        int inputNumber;

        do {
            System.out.println("Input number:");
            inputNumber = scanner.nextInt();

            if (inputNumber == 0) System.out.println("Exit.");
            else if (inputNumber == randomNumber) System.out.println("You guessed.");
            else if (inputNumber > randomNumber) System.out.println("Your number is bigger.");
            else System.out.println("Your number is less.");
        } while (inputNumber != 0);
    }
}
