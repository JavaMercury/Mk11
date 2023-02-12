package Beta;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(20);
        }
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
        SortAlgorithm. insertionSort(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println(RecursionAlgorithm.getFactorial(4));
    }
}
