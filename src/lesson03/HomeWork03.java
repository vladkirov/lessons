package lesson03;

import java.util.Arrays;

public class HomeWork03 {
    public static void main(String[] args) {
        final int minRandom = -5;
        final int maxRandom = 1;
        int diff = maxRandom - minRandom;

        final int amountNumbers = 100;
        int[] numbers = new int[amountNumbers];
        int[] statistic = new int[diff + 1];

        //int[] numbers = new int[]{0, -1, 1, -1, 1, 0, 0, 0, 0, -1, 1, 1, 1, 0, 1, 1, 1, 0, -1, 1};
        for (int i = 0; i < amountNumbers; i++) {
            numbers[i] = (int) (Math.random() * (diff + 1)) + minRandom;
            statistic[numbers[i] - minRandom] += 1;
        }
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(statistic));

        int[] equalStat = statistic.clone();
        Arrays.sort(equalStat);
        System.out.println(Arrays.toString(equalStat));

        boolean equals = false;
        for (int i = 0; i < equalStat.length - 1; i++)
            if (equalStat[i] == equalStat[i + 1]) equals = true;

        if (!equals)
            for (int curElem = 0; curElem < statistic.length; curElem++)
                if (statistic[curElem] == equalStat[equalStat.length - 1])
                    System.out.println("Maximum number in array: " + (curElem + minRandom));
    }
}
