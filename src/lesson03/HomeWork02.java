package lesson03;

import java.util.Arrays;

public class HomeWork02 {
    public static void main(String[] args) {
        int maxRange = 20;
        int step = 2;
        int amountElements = maxRange / step;
        int[] numbers = new int[amountElements];

        for (int i = 0; i < amountElements; i++) {
            numbers[i] = i * step + step;
        }
        System.out.println("Created array:");
        System.out.println(Arrays.toString(numbers));

        System.out.println("Inverted order:");
        for (int i = amountElements - 1; i > 0; i--) {
            System.out.print(numbers[i] + " ");
        }
    }
}
