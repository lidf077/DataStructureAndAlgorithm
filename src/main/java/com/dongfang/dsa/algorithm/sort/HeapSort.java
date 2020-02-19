package com.dongfang.dsa.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序可以认为是对选择排序的一种优化，用堆来优化寻找最值的时间复杂度
 *      是不稳定的排序
 *          时间 NlogN
 *          空间 1
 *      执行流程：
 *          1、对序列进行原地建堆 heapify
 *          2、重复执行以下操作，直到堆的元素数量为1
 *              2-1、交换堆顶元素与尾元素
 *              2-2、堆的元素数量减1
 *              2-3、对0位置进行1次siftDown操作
 * @param <E>
 */
public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    private int heapSize;

    @Override
    protected void sort() {
        heapSort();
    }

    private void heapSort() {

        heapSize = array.length;
        // 原地建堆
        heapify();
        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(0, heapSize - 1);

            // 堆的大小减1
            heapSize--;

            // 对0位置进行shitDown()恢复大顶堆的性质
            siftDown(0);

        }
    }


    /**
     * 批量建堆
     */
    private void heapify() {

        // 自下而上的下滤
        // 左右先建堆，再合并
        // n
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 让index位置的元素下滤
     *
     * @param index
     */
    private void siftDown(int index) {
        E element = array[index];
        int half = heapSize >> 1;
        // 第一个叶子节点的索引 == 非叶子节点的数量
        // index < 第一个叶子节点的索引
        // 必须保证index位置是非叶子节点
        while (index < half) {
            // index的节点有2种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;

            // 选出左右子节点最大的那个
            if (rightIndex < heapSize && compare(array[rightIndex], child) > 0) {
                child = array[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) break;

            // 将子节点存放到index位置
            array[index] = child;
            // 重新设置index
            index = childIndex;
        }
        array[index] = element;
    }
}
