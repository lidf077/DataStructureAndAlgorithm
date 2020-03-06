package com.dongfang.dsa.algorithm.recursion;

import org.junit.Test;

public class Fib { // 自顶向下 public int fibByRecursion(int N) { if (N <= 0) return 0; if (N <= 2) return 1; // 先递归左边的，递归右边时，有重复计算，往树的两侧计算 return fib(N - 1) + fib(N - 2); }

    @Test
    public void test() {
        System.out.println("fib(4) = " + fib(44));
    }


    public int fib(int N) {
        if (N < 2) return N;
        int[] arr = new int[N + 1];
        arr[1] = 1;
        arr[2] = 1;
        return helper1(N, arr);
    }

    private int helper1(int n, int[] arr) {
        // 只往树的一侧计算
        if (arr[n] == 0)
            arr[n] = (arr[n - 1] == 0 ? helper(n - 1, arr) : arr[n - 1]) + (arr[n - 2] == 0 ? helper(n - 2, arr) : arr[n - 2]);
        return arr[n];
    }

    // 自顶向下
    private int helper(int n, int[] arr) {
        // 只往树的一侧计算
        if (arr[n] == 0) arr[n] = helper(n - 1, arr) + helper(n - 2, arr);
        return arr[n];
    }

    // 去除递归调用，
//  是一种自底向上的计算过程，先算小的，再算大的
    public int fibByIterate(int n) {
        if (n <= 2) return 1;
        int[] arr = new int[n + 1];
        arr[1] = arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * 每次计算，只用到了两个元素
     */
    public int fibByTwoNum(int N) {
        if (N <= 0) return 0;
        if (N <= 2) return 1;
        int first = 1;
        int second = 1;
        int third;
        while (N > 0) {
            third = first + second;
            first = second;
            second = third;
            N--;
        }
        return second;
    }

    /**
     * 使用滚动数组，注意这个思想是很重要的，对数据下标求余数，可以在数组上循环
     *
     * @param n
     * @return
     */
    public int fibByRollArray(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i % 2] = array[(i - 1) % 2] + array[(i - 2) % 2];
        }
        return array[n % 2];
    }
}