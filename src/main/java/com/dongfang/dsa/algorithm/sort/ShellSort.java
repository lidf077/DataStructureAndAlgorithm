package com.dongfang.dsa.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Shell排序：
 *     1、希尔排序把序列看作是一个矩阵，分成m列，逐列进行排序
 *     2、m从某个整数逐渐减为1
 *     3、当m为1时，整个序列将完全有序
 *     4、因此，希尔排序也被称为递减增量排序
 *     5、矩阵的列数取决于步长
 *         比如，如果步长序列为{1, 5, 9, 41, 109...}，就代表依次分成109列，41列，19列，5列，1列
 *         不同的步长序列，执行效率也不同
 *     不稳定的排序算法
 * @param <E>
 */

public class ShellSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        shellSort();
    }

    private void shellSort() {
        // // 8, 4, 2, 1
        List<Integer> stepSequence = shellStepSequence();
        System.out.println("Arrays.toString(stepSequence.toArray()) = " + Arrays.toString(stepSequence.toArray()));
        for (Integer step : stepSequence) {
            sortByStep(step);
        }
    }

    /**
     * 分成step列来排序
     * 是对插入排序的改进
     * 原始的插入排序是以step为1来排序， shell排序是有一个step序列，用这个step不断进行插入排序
     * 原来 +1 的地方， +step
     * 原来 -1 的地方， -step
     * 目前最好的序列，由Robert Sedgewick提出，最坏时间复杂度为 O(N^4/3)
     * 时间复杂度取决于步长序列
     *
     * @param step
     */
    private void sortByStep(int step) {
        // col 第几列

        // 对 [0, array.length)范围内的元素进行插入排序，间隔为step
        for (int begin = step; begin < array.length; begin += step) {
            for (int cur = begin; cur > 0; cur -= step) {
                if (compare(cur, cur - step) < 0) swap(cur, cur - step);
            }

        }
    }

    /**
     * 使用shell设计的步长序列，shell排序的本质就是不断更改步长序列
     * 8, 4, 2, 1
     *
     * @return
     */
    private List<Integer> shellStepSequence() {
        ArrayList<Integer> stepSequence = new ArrayList<>();
        int step = array.length;

        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }
}
