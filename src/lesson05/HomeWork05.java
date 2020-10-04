package lesson05;

public class HomeWork05 {
    public static void main(String[] args) {
        String originalString = "в предложении все слова разной длины";
        System.out.println("Original string: " + originalString);
        String[] words = originalString.split(" ");
        String maxLengthWord = "";
        int maxLength = Integer.MIN_VALUE;

        for (String word : words)
            if (word.length() > maxLength) {
                maxLengthWord = word;
                maxLength = word.length();
            }

        System.out.println("Max length word: " + maxLengthWord);
    }
}
