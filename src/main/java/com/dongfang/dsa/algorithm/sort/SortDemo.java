package com.dongfang.dsa.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class SortDemo {
    private static final Integer[] data =
            {45, 7, 34, 21, 17, 73, 94, 58, 100,
                    22, 40, 3, 72, 29, 9, 56, 52, 92,
                    49, 89, 46, 18, 70, 35, 11, 53, 24,
                    37, 84, 82, 85, 16, 28, 90, 32, 54,
                    97, 79, 26, 65, 31, 78, 95, 98, 33,
                    83, 27, 39, 55, 61, 23, 86, 76, 62,
                    60, 88, 48, 96, 4, 20, 42, 10, 41,
                    50, 81, 6, 5, 13, 57, 47, 67, 75, 59,
                    77, 64, 68, 71, 25, 38, 2, 80, 1, 12, 93};

    @Test
    public void testBubbleSort() {
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        bubbleSort.sort(data);
        System.out.println("Arrays.toString(data) = " + Arrays.toString(data));
    }

    @Test
    public void testSelectionSort() {
        SelectionSort<Integer> selectionSort = new SelectionSort<>();
        selectionSort.sort(data);
        System.out.println(Arrays.toString(data));
    }


    @Test
    public void testHeapSort() {
        Sort<Integer> heapSort = new HeapSort<>();

        heapSort.sort(data);
        System.out.println("Arrays.toString(data) = " + Arrays.toString(data));
    }

    @Test
    public void testInsertionSort() {
        InsertionSort<Integer> insertionSort = new InsertionSort<>();
        insertionSort.sort(data);
        System.out.println("Arrays.toString(data) = " + Arrays.toString(data));

    }
}
