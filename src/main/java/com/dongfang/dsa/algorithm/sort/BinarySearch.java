package com.dongfang.dsa.algorithm.sort;

import org.junit.Test;

public class BinarySearch {
    private static int[] data = {1, 2, 3, 4, 6, 6, 6, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 41, 42, 43, 44, 45, 46, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 80, 81, 82, 83, 84, 85, 86, 87, 88, 90, 91, 92, 94, 95, 96, 97, 98, 99, 100};


    @Test
    public void testBinarySearch() {
        int index = indexOf(data, 6);
        System.out.println("index = " + index);
        int index1 = "heeeelllo".indexOf('l');
        System.out.println("index1 = " + index1);
    }

    @Test
    public void testInsert() {
                     //0  1  2  3  4   5   6
        int[] array = {2, 4, 8, 8, 8, 12, 14};
        int index = search(array, 77);
        System.out.println("index = " + index);

    }


    // 找不到，返回-1

    /**
     * 查到target在有序数组中的位置
     * 假设在[begin, end)范围内搜索某个元素target, mid = (begin + end )/ 2
     * 1、如果target < m 去[begin, mid)范围二分搜索
     * 2、如果target > m 去[mid + 1, end)范围二分搜索
     * 3、如果 target == m，直接返回mid
     *
     * @param array
     * @param target
     * @return
     */
    public static int indexOf(int[] array, int target) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length; // end为最后一个元素的index + 1
        // [begin, end) end - begin 元素数量
        while (begin < end) { // 区间内至少有一个元素
            int mid = (begin + end) >> 1;
            if (target < array[mid]) { // 左边区间
                end = mid;
            } else if (target > array[mid]) { // 右边区间
                begin = mid + 1;
            } else { // array[mid] = target;
                return mid;
            }
        }
        System.out.println("begin = " + begin);
        System.out.println("end = " + end);

        return -1;
    }


    /**
     * 查找target在有序数组中的插入位置
     * 要求二分搜索返回的插入位置，第一个大于target的元素位置
     * 假设在[begin, end)范围内搜索某个元素target, mid = (begin + end )/ 2
     * 1、如果target < m 去[begin, mid)范围二分搜索
     * 2、如果target >= m 去[mid + 1, end)范围二分搜索，注意这里是>=
     * 3、如果 target == m，直接返回mid
     * 最后begin end就是target要插入的位置
     *
     * @return
     */
    public static int search(int[] array, int target) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target < array[mid]) {
                end = mid;
            } else if (target >= array[mid]) {
                begin = mid + 1;
            }
        }
        return begin;
    }
}