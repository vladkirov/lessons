package lesson05;

import java.util.Arrays;
import java.util.Scanner;

public class HomeWork01 {
    public static void main(String[] args) {
        System.out.print("Input amount elements of array: ");
        Scanner scanner = new Scanner(System.in);
        int amountElements = scanner.nextInt();
        scanner.nextLine();

        String[] words = new String[amountElements];

        int addedElements = 0;
        final String exitWord = "exit";

        System.out.println("Now input any words or <exit> for stop working.");
        while (amountElements != addedElements) {
            System.out.println("Input word:");
            String inputWord = scanner.nextLine();
            if (inputWord.equals(exitWord)) {
                break;
            } else {
                boolean elementExist = false;
                for (int i = 0; i < addedElements; i++) {
                    if (words[i].equals(inputWord)) {
                        elementExist = true;
                        break;
                    }
                }
                if (!elementExist) words[addedElements++] = inputWord;
            }
        }
        System.out.println("Array with unique elements:");
        System.out.println(Arrays.toString(words));
    }
}
