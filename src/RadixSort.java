import java.util.Arrays;

public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int max = arr[0];
        for (int num : arr) {
            if (num < 0) {
                throw new IllegalArgumentException("Radix sort example only supports non-negative numbers.");
            }
            if (num > max) {
                max = num;
            }
        }

        int[] output = new int[arr.length];
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, output, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int[] output, int exp) {
        int[] count = new int[10];

        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};

        System.out.println("Before sort: " + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("After sort: " + Arrays.toString(arr));
    }
}
