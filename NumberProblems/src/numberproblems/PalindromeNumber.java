package numberproblems;

public class PalindromeNumber {

    public static void main(String[] args) {

        int n = 121;
        int temp = n;
        int reverse = 0;

        while (n > 0) {

            int r = n % 10;
            reverse = reverse * 10 + r;
            n = n / 10;
        }

        if (temp == reverse) {
            System.out.println("Palindrome");
        }
        else {
            System.out.println("Not Palindrome");
        }
    }
}