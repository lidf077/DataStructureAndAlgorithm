package com.dongfang.dsa.structure.ch7_heap;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTrees;
import org.junit.Test;

public class HeapDemo {
    @Test
    public void testHeapAdd() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        int[] data = {10, 38, 43, 50, 65, 68, 72};
        for (int datum : data) {
            heap.add(datum);
        }
        BinaryTrees.println(heap);
        heap.add(90);
        BinaryTrees.println(heap);
    }
}
