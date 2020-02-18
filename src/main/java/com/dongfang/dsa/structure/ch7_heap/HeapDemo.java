package com.dongfang.dsa.structure.ch7_heap;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTrees;
import org.junit.Test;

import java.util.Comparator;
import java.util.zip.DataFormatException;

public class HeapDemo {
    @Test
    public void testHeapAdd() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        int[] data = {10, 38, 43, 50, 65, 68, 72, 90};
        for (int datum : data) {
            heap.add(datum);
        }
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.replace(2);
        BinaryTrees.println(heap);
    }

    @Test
    public void testHeapify() {
        Integer[] data = {68, 31, 90, 3, 100, 75, 81, 80, 16, 51, 39, 43, 52, 49, 36, 5, 99, 27, 45, 65};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data);

        BinaryTrees.println(heap);
    }

    @Test
    public void testSmallHeap() {
        Integer[] data = {68, 31, 90, 3, 100, 75, 81, 80, 16, 51, 39, 43, 52, 49, 36, 5, 99, 27, 45, 65};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        BinaryTrees.println(heap);
    }

    @Test
    public void testTopK() {
        Integer[] data = {45, 7, 34, 21, 17, 73, 94, 58, 22, 40, 3, 72, 29,
                9, 56, 52, 92, 49, 89, 46, 18, 70, 35, 11, 53, 24, 37,
                84, 82, 85, 16, 28, 90, 32, 54, 97, 79, 26, 65, 31, 78,
                95, 98, 33, 83, 27, 39, 55, 61, 23, 86, 76, 62, 60, 88,
                48, 96, 4, 20, 42, 10, 41, 50, 81, 6, 5, 13, 57, 47, 67,
                75, 59, 77, 64, 68, 71, 25, 38, 2, 80, 1, 12, 93};

        BinaryHeap<Integer> heap = new BinaryHeap<>((o1, o2) -> o2 - o1);

        int k = 5;

        for (Integer datum : data) {
            if (heap.size() < k) { // 前k个数添加到小顶堆
                heap.add(datum); // logk
            } else if (datum > heap.get()) { // 如果是第k + 1个数，并且大于堆顶元素
                heap.replace(datum); // logk
            }
        }
        // O(nlogk)
        BinaryTrees.println(heap);

    }
}
