package arraybasics;

public class PositiveNegativeCount {

    public static void main(String[] args) {

        int[] arr = {10, -5, 20, -8, 15, -2};

        int positive = 0;
        int negative = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > 0) {
                positive++;
            }
            else if (arr[i] < 0) {
                negative++;
            }
        }

        System.out.println("Positive Count = " + positive);
        System.out.println("Negative Count = " + negative);
    }
}