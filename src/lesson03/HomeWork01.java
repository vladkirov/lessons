package lesson03;

import java.util.Arrays;
import java.util.Scanner;

public class HomeWork01 {
    public static void main(String[] args) {
        System.out.println("Input amount elements of array:");
        Scanner scanner = new Scanner(System.in);
        int amountNumbers = scanner.nextInt();
        double average = 0.0;

        int[] numbers = new int[amountNumbers];
        for (int i = 0; i < amountNumbers; i++) {
            numbers[i] = (int) (Math.random() * Integer.MAX_VALUE);
            average += numbers[i];
        }
        average /= amountNumbers;
        System.out.println("Array not sorted:");
        System.out.println(Arrays.toString(numbers));
        System.out.println("Average: " + average);

        Arrays.sort(numbers);
        System.out.println("Array sorted:");
        System.out.println(Arrays.toString(numbers));
        System.out.println("Minimum: " + numbers[0]);
        System.out.println("Maximum: " + numbers[amountNumbers - 1]);
    }
}
