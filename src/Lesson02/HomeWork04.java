package Lesson02;

public class HomeWork04 {
    public static void main(String[] args) {
        int minValueInterval = 25;
        int maxValueInterval = 200;

        int minRandom = 10;
        int maxRandom = 500;
        int diff = maxRandom - minRandom;
        int randomNumber = (int) (Math.random() * ++diff) + minRandom;

        if (randomNumber >= minValueInterval && randomNumber <= maxValueInterval) {
            System.out.print("Число " + randomNumber + " содержится в интервале (" + minValueInterval + ";" + maxValueInterval + ")");
        } else
            System.out.print("Число " + randomNumber + " не содержится в интервале (" + minValueInterval + ";" + maxValueInterval + ")");
    }
}
