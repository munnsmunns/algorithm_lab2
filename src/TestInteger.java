import com.sun.corba.se.impl.orb.ParserTable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class TestInteger implements Comparable<TestInteger> {

    public int value;
    public static long counter;

    public TestInteger(int v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(TestInteger o) {
        counter++;
        return this.value - o.value;
    }

    public static void quickSort(TestInteger[] arr, int first, int last) {
        // if the size of the area is 10 or less, use tim sort on that area
        if(last - first <= 10) {
            Arrays.sort(arr, first, last);
            return;
        }

        // select the first item as the pivot
        int pivot = first;
        TestInteger pivotValue = arr[first];
        int unknown = pivot + 1;

        while(unknown < last) {
            // compare the pivot with the next unknown item
            // if the unknown value is less than the pivot, swap with arr[pivot +1]
            if(pivotValue.compareTo(arr[unknown]) < 0) {
                TestInteger tmp = arr[pivot + 1];
                arr[pivot + 1] = arr[unknown];
                arr[unknown] = tmp;
                pivot++;
            }
            // else the unknown value is in the correct place
            unknown++;
        }

        // put the pivot value in its correct place
        arr[first] = arr[pivot];
        arr[pivot] = pivotValue;

        // call the quicksort again on the sections on either side of the pivot
        TestInteger.quickSort(arr, first, pivot);
        TestInteger.quickSort(arr, pivot + 1, last);

    }

    public static void main(String[] args) {

        Random random = new Random();

        TestInteger[] arr1 = new TestInteger[10000];
        TestInteger[] arr2;

        // put random values in the array

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = new TestInteger(random.nextInt(1000000));
        }

        // make a second array that is a clone of the first

        arr2 = arr1.clone();

        // tim sort
        Arrays.sort(arr1);
        System.out.println("Number of comparisons using Tim Sort for random numbers: " + TestInteger.counter);

        TestInteger.counter = 0;

        // quicksort
        TestInteger.quickSort(arr2, 0, arr2.length);
        System.out.println("Number of comparisons using quicksort for random numbers: " + TestInteger.counter);

        // sort an array that's already been sorted
        Arrays.sort(arr1);
        System.out.println("Number of comparisons using Tim Sort for sorted numbers: " + TestInteger.counter);

        // TODO: sort with quicksort

        // create an array with 10 sorted sequences of 1000 elements each
        arr1 = new TestInteger[10000];

        for (int i = 0; i < 10; i++) {

            arr1[i*1000] = new TestInteger(random.nextInt(1000000));

            for (int j = 1000 * i + 1; j < 1000 * (1 + i); j++) {
                arr1[j] = new TestInteger(arr1[j-1].value + 1);
            }
        }

        arr2 = arr1.clone();
        Arrays.sort(arr1);
        System.out.println("Number of comparisons using Tim Sort for 10 sorted sequences: " + TestInteger.counter);

        // create an array with 100 sorted sequences of 100 elements each
        arr1 = new TestInteger[10000];

        for (int i = 0; i < 100; i++) {

            arr1[i*100] = new TestInteger(random.nextInt(1000000));

            for (int j = 100 * i + 1; j < 100 * (1 + i); j++) {
                arr1[j] = new TestInteger(arr1[j-1].value + 1);
            }
        }

        arr2 = arr1.clone();
        Arrays.sort(arr1);
        System.out.println("Number of comparisons using Tim Sort for 100 sorted sequences: " + TestInteger.counter);
    }
}

