package lesson05;

public class HomeWork02 {
    public static void main(String[] args) {
        String inputString = "дом домик домой одомашненный дом";
        String Pattern = "дом";

        System.out.println(inputString.split(Pattern).length - 1);
    }
}
