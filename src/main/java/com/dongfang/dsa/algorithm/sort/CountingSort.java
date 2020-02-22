package com.dongfang.dsa.algorithm.sort;

public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        countingSortOpt();
    }

    /**
     * 无法对负整数进行排序
     * 极其浪费内存空间
     * 是不稳定的排序
     */
    private void countingSort() {
        // 找出最大值 max
        int max = array[0];
        for (Integer integer : array) {
            if (integer > max) max = integer;
        }

        // 开辟内存空间，存储每个整数出现的次数
        int[] counts = new int[max + 1];
        // 统计每个整数出现的次数
        for (Integer integer : array) {
            counts[integer]++;
        }

        // 根据整数出现次数，对整数进行排序
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                array[index] = i;
                index++;
                counts[i]--;
            }
        }
    }


    /**
     * 最好，最坏，平均时间复杂度为O(n+k)
     * 空间复杂度O(n+k)
     * k 是整数的取值范围，所以适合对一定范围内的数据排序，范围过大，会导致时间，空间复杂度很高
     * 属于稳定排序
     * 如果自定义对象可以提供用以排序的整数类型，依然可以使用计数排序
     *
     */
    private void countingSortOpt() {
        // 找出最值 max min
        int max = array[0];
        int min = array[0];
        for (Integer integer : array) {
            if (integer > max) max = integer;
            if (integer < min) min = integer;
        }

        // 开辟内存空间，存储次数，累加了前面的次数
        int[] counts = new int[max - min + 1];
        // 统计每个整数出现的次数
        // 元素值-min才是索引
        for (Integer integer : array) {
            counts[integer - min]++;
        }

        // 累加次数 前面有多少个元素
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i - 1] + counts[i];
        }

        // 从后往前遍历，确定将它放在有序数组中的什么位置
        // 从后往前遍历，能保证排序稳定
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            // array[i] - min counts数组中的索引
            // counts[array[i] - min] 元素前面其他元素的累加个数
            newArray[--counts[array[i] - min]] = array[i];
        }

        // 将有序数组newArray拷贝到array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}