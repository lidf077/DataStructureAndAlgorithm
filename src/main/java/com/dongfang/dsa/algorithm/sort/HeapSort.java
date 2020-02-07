package com.dongfang.dsa.algorithm.sort;

public class HeapSort<T extends Comparable> extends Sort<T> {
    private int heapSize;

    @Override
    protected void sort() {

    }

    private void heapSort() {
        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(0, heapSize - 1);

            // 堆的大小减1
            heapSize--;

            // 对0位置进行shiftDown()恢复堆的性质

        }
    }
}
