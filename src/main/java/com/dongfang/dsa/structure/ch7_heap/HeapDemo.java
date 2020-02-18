package com.dongfang.dsa.structure.ch7_heap;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTrees;
import org.junit.Test;

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
        int[] data = {68, 31, 90, 3, 100, 75, 81, 80, 16, 51, 39, 43, 52, 49, 36, 5, 99, 27, 45, 65};
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        for (int datum : data) {
            heap.add(datum);
        }

        BinaryTrees.println(heap);
    }
}
