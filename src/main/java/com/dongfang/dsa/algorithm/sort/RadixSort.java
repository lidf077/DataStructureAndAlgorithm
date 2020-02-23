package com.dongfang.dsa.algorithm.sort;

/**
 * 基数排序 进制排序 Radix Sort
 * 1、基数排序非常适用于整数排序，尤其是非负整数
 * 2、执行流程：依次对个位数，十位数，百位数，千位数，万位数进行排序，从低位到高位
 * 3、个位数，十位数，百位数的取值范围都是固定的0-9，可以使用计数排序对它们进行排序
 * 4、先对高位排序，不可行
 *
 *      最好，最坏，平均时间最复杂度为O(d*(n+k))，d是最大值的位数，k是进制，属于稳定排序
 *      空间复杂度为O(n+k)
 */
public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        radixSort();
    }

    private void radixSort() {
        int max = array[0];
        for (Integer element : array) {
            if (element > max) max = element;
        }

        // max = 593，要求某个位的基数，将其他位的消除掉就好
        /**
         * 个位数：593 % 10
         * 十位数：593 / 10 % 10
         * 百位数：593 / 100 % 10
         * 千位数：593 / 1000 % 10
         */
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSortOpt(divider);
        }
    }

    // 使用计数排序对基数进行排序
    private void countingSortOpt(int divider) {
        // 开辟内存空间，存储次数，累加了前面的次数
        int[] counts = new int[10];
        // 统计每个整数出现的次数
        // 元素值-min才是索引
        for (Integer element : array) {
            counts[element / divider % 10]++;
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
            newArray[--counts[array[i] / divider % 10]] = array[i];
        }

        // 将有序数组newArray拷贝到array
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
