import java.util.Arrays;
import java.util.Random;

public class BitonicSort {

    /**
     * sorts an array of numeric values using bitonic merge sort
     *
     * @param direction determines the sorting order, true = ascending, false = descending
     * @param arr       an array of numeric values to be sorted. The input array must have a length
     *                  which is a power of two: 2, 4, 8, 16, 32, 128, 256, ...
     * @return a sorted array of numeric values
     */
    private
    static int[] bitonicSort(boolean direction, int[] arr) {

        //if the array contains one or less elements it does not have to be sorted
        if (arr.length <= 1) {
            return arr;
        }

        /* else: split the array in two sequences and sort the first part in ascending order,
        then the second part in descending order. After sorting the two halves, merge them again
        to create a bitonic sequence with its first half ascending and its second half descending.
        */
        else {

            int[] first = Arrays.copyOfRange(arr, 0, arr.length / 2);
            //printArray(first);

            int[] second = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
            //printArray(second);

            bitonicSort(true, first);
            bitonicSort(false, second);

            return bitonicMerge(direction, combine(first, second));
        }
    }

    /**
     * auxiliary method for merging two arrays into one
     *
     * @param a 1st input array
     * @param b 2nd input array
     * @return result as output array which contains a merged with b
     */
    private static int[] combine(int[] a, int[] b) {

        //creates a new array with the size of a.length + b.length
        int[] result = new int[a.length + b.length];

        //copy array a to first half of the result array
        System.arraycopy(a, 0, result, 0, a.length);

        //copy array b to the second half of the result array
        System.arraycopy(b, 0, result, a.length, b.length);

        return result;
    }

    /**
     * merges two bitonic sequences into into one sequence
     *
     * @param direction the sorting order (true = ascending, false = descending)
     * @param arr       the input array
     * @return result, array which is a bitonic sequence
     */
    private static int[] bitonicMerge(boolean direction, int[] arr) {

        // if the array contains only one element, it cannot be merged
        if (arr.length == 1) {
            return arr;
        }

        /*
        else use compare() to compare and sort the elements in the array
        then recursively split and merge the first and the second half of the array
         */
        else {

           bitonicCompare(direction, arr);

            int[] first = Arrays.copyOfRange(arr, 0, arr.length / 2);
            int[] second = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

            first = bitonicMerge(direction, first);
            second = bitonicMerge(direction, second);

            //return result as a bitonic sequence consisting of the first and second half
            return combine(first, second);
        }
    }

    /**
     * compares the elements in an array with a stepwidth which is the length of the array / 2.
     *
     * @param direction determines the sorting order (true = ascending, false = descending)
     * @param arr       array of elements to be compared
     * @return arr as a sorted array of elements
     */
    private static void bitonicCompare(boolean direction, int[] arr) {

        //assign distance the value of the half length of the array
        int distance = arr.length / 2;

        //do i comparisons between the elements
        //i == distance, as with n elements and n = arr.length, we need n/2 comparisons
        for (int i = 0; i < distance; i++) {

            //if the first element is larger than the next element -> true
            //if the first element is smaller than the next element -> false
            //if direction == true, this results in a swap which sorts the elements to an ascending order
            //if direction == false, this results to a swap which sorts the elements to a descending order


            if ((arr[i] > arr[i + distance]) == direction) {

                //swapping elements
                int temp = arr[i];
                arr[i] = arr[i + distance];
                arr[i + distance] = temp;
            }

        }
    }


    /**
     * auxiliary method to print an array from start to end
     *
     * @param arr array to be printed
     */
    private static void printArray(int[] arr) {

        for (int value : arr) {

            System.out.println(value);
        }

    }

    public static void main(String[] args) {

        /* Leider funktioniert das noch nicht so richtig.
         * Arrays mit einer Laenge von 2 werden korrekt sortiert.
         * Bei einer Laenge > 2 wird zu frueh abgebrochen, also nur bis zu einem bestimmten Punkt sortiert.
         * Ich habe irgendwo nen Denkfehler, aber ich komme leider nicht drauf...
         */

        final int MAX = 8;
        Random r = new Random();
        int[] arr = new int[MAX];

        for (int i = 0; i < MAX; i++) {

            arr[i] = r.nextInt(100);
        }

        printArray(arr);
        System.out.println("++++++++++++");
        int[] sortedArray = bitonicSort(true, arr);
        printArray(sortedArray);
    }

}