package arraybasics;

public class LargestElement {

    public static void main(String[] args) {

        int[] arr = {10, 45, 20, 80, 30};

        int largest = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        System.out.println("Largest = " + largest);
    }
}