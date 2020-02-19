package com.dongfang.dsa.algorithm.sort;

/**
 * 插入排序非常类似于扑克牌的排序
 *      执行流程：
 *          1、在执行过程中，插入排序会将序列分为2部分
 *              头部是已经排好序的，尾部是待排序的
 *          2、从头开始扫描每一个元素
 *              每当扫描到一个元素，就将它插入到头部合适的位置，使得头部数据依然保持有序
 * @param <E>
 */
public class InsertionSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        insertionSort();
    }

    private void insertionSort() {
        int len = array.length;
        // 从第一张牌开始，第0张已经有序
        for (int begin = 1; begin < len; begin++) {
            for (int cur = begin; cur > 0; cur--) {
                // 小于才交换，所以是稳定的
                if (compare(cur, cur - 1) < 0) swap(cur, cur - 1);
            }
        }
    }
}
