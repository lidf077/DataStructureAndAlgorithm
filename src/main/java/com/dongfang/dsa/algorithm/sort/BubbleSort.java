package com.dongfang.dsa.algorithm.sort;

// 比较大的值一直往后边冒
public class BubbleSort<T extends Comparable> extends Sort<T> {
    @Override
    protected void sort() {
        optimizedBubbleSortIndex();
    }

    private void optimizedBubbleSortIndex() {
        for (int end = array.length - 1; end > 0; end--) {
            // 初始值为完全有序做准备，在数组完全有序的时候有用，数组完全有序的时候，循环一次就退出
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (compare(begin - 1, begin) > 0) {
                    swap(begin - 1, begin);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

    private void optimizedBubbleSort() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean isSorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (compare(begin - 1, begin) > 0) {
                    isSorted = false;
                    swap(begin - 1, begin);
                }
            }
            if (isSorted) break;
        }
    }

    private void bubbleSort() {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (compare(begin - 1, begin) > 0) {
                    swap(begin - 1, begin);
                }
            }
        }
    }
}
