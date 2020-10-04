package lesson05;

public class HomeWork03 {
    public static void main(String[] args) {
        String originalString = "re t 02 0ter";
        boolean isPalindrom = true;

        originalString = originalString.replace(" ", "").toLowerCase();
        for (int i = 0; i < (originalString.length() / 2); i++)
            if (originalString.charAt(i) != originalString.charAt(originalString.length() - i - 1)) {
                isPalindrom = false;
                break;
            }

        System.out.println("Is palindrom: " + isPalindrom);
    }
}
