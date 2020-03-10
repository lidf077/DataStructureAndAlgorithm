package com.dongfang.dsa.acm._02__02_searchAndSort;

import org.junit.Test;

import java.util.Random;

/**
 * 二分查找等价于3个子问题
 * 1、左边找（递归）
 * 2、中间比
 * 3、右边找（递归）
 */
public class 递归二分搜索 {
    @Test
    public void testBinarySearch() {
        int[] ints = new Random().ints(10000 * 10000, 0, 1000000).toArray();
        int result = binarySearch(ints, ints[9999]);
        System.out.println("result = " + result);
    }

    private int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) return i;
        }
        return -1;
    }

    private int binarySearch(int[] arr, int target) {
        int first = 0, last = arr.length;

        while (first < last) {
            int mid = first + (last - first) >> 1;
            if (target > arr[mid]) {
                first = mid + 1;
            } else if (target < arr[mid]) {
                last = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
