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



    private void insertionSortOpt() {
        int len = array.length;
        for (int begin = 1; begin < len; begin++) {
            int cur = begin;
            E value = array[cur];
            // 将元素向后面挪动，value暂存起来，不要一直要cur指针上的值交换，减少交换的三次操作
            for (; cur > 0 && compare(value, array[cur - 1]) < 0; cur--) {
                array[cur] = array[cur - 1];
            }
            array[cur] = value;
        }
    }

    /**
     * 二分搜索优化
     * 在元素value插入的过程中，可以二分搜索出合适的插入位置，然后再将元素v插入
     * 挪动是一样的，但是二分搜索优化了比较次数
     */

    private void insertionSortOptByBinarySearch() {
        int len = array.length;
        for (int begin = 1; begin < len; begin++) {
            insert(begin, search(begin));
        }
    }

    /**
     * 将source位置的元素插入到dest位置
     * @param source
     * @param dest
     */
    private void insert(int source, int dest) {
        E target = array[source];
        // 将[insertIndex, begin)的向右挪动一个单位，大的先挪动，--
        for (int i = source; i > dest ; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = target;
    }

    /**
     * 利用二分搜索找到 index 位置元素的待插入位置
     * 已经排好序的数组区间 [0, index)
     *
     * @param index
     * @return
     */
    private int search(int index) {
        E target = array[index];
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (compare(target, array[mid]) < 0) {
                end = mid;
            } else if (compare(target, array[mid]) >= 0) {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
