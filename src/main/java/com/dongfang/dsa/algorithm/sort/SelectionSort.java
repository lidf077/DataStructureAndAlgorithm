package com.dongfang.dsa.algorithm.sort;

// 每一轮扫描中选出最值，交换位置
public class SelectionSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        selectionSort();
    }

    private void selectionSort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                // >= 排序就是稳定的
                if (compare(begin, maxIndex) >= 0) maxIndex = begin;
            }
            swap(maxIndex, end);
        }
    }
}
