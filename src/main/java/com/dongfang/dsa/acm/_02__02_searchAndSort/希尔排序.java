package com.dongfang.dsa.acm._02__02_searchAndSort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class 希尔排序 {
    @Test
    public void testShellSort() {
        int[] random = new Random().ints(10000000, 0, 100000000).toArray();
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        shellSort(random);
//        System.out.println(Arrays.toString(random));
    }

    private void shellSort(int[] arr) {
        int arrLen = arr.length;
        // 外层循环，不断缩小增量
        for (int interval = arrLen / 2; interval > 0; interval /= 2) {
            // 内层进行插入排序
            // 先写出间隔为1的插入排序，再将1换成interval
            for (int i = interval; i < arrLen; i += interval) {
                for (int j = i; j > 0; j -= interval) {
                    if (arr[j] < arr[j - interval]) {
                        swap(arr, j, j - interval);
                    }
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
