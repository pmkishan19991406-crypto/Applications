package numberproblems;

public class ArmstrongNumber {

    public static void main(String[] args) {

        int n = 153;
        int temp = n;
        int sum = 0;

        while (n > 0) {

            int r = n % 10;
            sum = sum + r * r * r;
            n = n / 10;
        }

        if (temp == sum) {
            System.out.println("Armstrong Number");
        }
        else {
            System.out.println("Not Armstrong Number");
        }
    }
}