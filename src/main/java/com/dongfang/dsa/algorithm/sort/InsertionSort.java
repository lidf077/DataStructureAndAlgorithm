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

    /**
     * 插入排序---逆序对 inversion
     *      1、什么是逆序对
     *          数组 2 3 8 6 1的逆序对为2 1， 3 1， 8 6， 8 1， 6 1
     *      2、插入排序的时间复杂度与逆序对的数量成正比关系
     *          逆序对的数量越多，插入排序的时间复杂度越高
     *      3、最坏，平均时间复杂度为 O(N^2)，最好的时间复杂度是O(N)属于稳定排序
     *      4、当逆序对的数量极少时，插入排序的效率特别高，速度甚至比O(nlogn)的快速排序还要快
     *      5、数据量不是特别大的时候，插入排序的效率也是非常好的
     */
}
