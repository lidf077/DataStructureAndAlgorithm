package com.dongfang.dsa.acm._02_01_recursion;

import org.junit.Test;

import java.util.Arrays;

public class 什么是递归 {
    private void fun(int i) {
        // 没有递归终止条件，就会出现StackOverflowError
        //if (i == 0) return;
        // 调用自身
        fun(i - 1);
    }

    @Test
    public void testInfinitiveRecursion() {
        // java.lang.StackOverflowError
        fun(100);
    }


    /**
     * f(n)是求n的阶乘---->f(n-1)就是求n-1的阶乘，必安理的
     * 求n的阶乘
     *      1、找重复: f(n) = n * f(n-1) 求n-1的阶乘是原问题的重复（规模更小）---子问题
     *      2、找变化：变化的量作为参数 n在变化
     *      3、找边界：变到什么时候是个头，因此进来就要做边界或者出口的判断
     */
    private int fractional(int n) {
        // 进来就判断边界
        if (n == 1) return 1; //减到1就不能减了
        return n * fractional(n - 1);
    }

    /**
     * 我只做一部分，其他的你做
     * 打印i--j
     *      1、找重复:
     *      2、找变化：
     *      3、找边界：变化的最终趋势
     *          我打i，你打 (i + 1, j)
     */
    private void printNatureNumber(int i, int j) {
        if (i > j) return;
        System.out.println(i);
        printNatureNumber(i + 1, j);
    }
    @Test
    public void testPrintNatureNumber() {
        printNatureNumber(13, 10);
    }

    /**
     * 数组求和
     *      1、找重复: 将数组取出一块 arr[start] + sumOfArrHelper(arr, start + 1, end)
     *      2、找变化：
     *      3、找边界：数组的长度在变化
     *      数组的递归一般要加参数，因为数组体现不出变化，只有加关于下标的参数
     *
     */
    private int sumOfArr(int[] arr) {
        return sumOfArrHelper(arr, 0, arr.length - 1);
    }
    private int sumOfArrHelper(int[] arr, int start, int end) {
        if (start == end) return arr[start];
        return arr[start] + sumOfArrHelper(arr, start + 1, end);
    }
    @Test
    public void testSumOfArr() {
        int[] arr = {1, 2, 3, 4};
        int sum = sumOfArr(arr);
        System.out.println("sum = " + sum);
    }

    /**
     * 翻转字符串
     *      1、找重复:  abcd ---> d + reverse(abc)
     *      2、找变化：
     *      3、找边界： 只有一个字符的时候，不用翻转了，直接返回
     *
     */
    private String reverseString(String src) {
        return reverseStringHelper2(src, 0);
    }
    private String reverseHelper(String src, int end) {
        if (end == 0) return src.charAt(end) + "";
        return src.charAt(end) + reverseHelper(src, end - 1);
    }
    private String reverseStringHelper2(String src, int begin) {
        if (begin == src.length() - 1) return src.charAt(begin) + "";
        return reverseStringHelper2(src, begin + 1) + src.charAt(begin);
    }
    @Test
    public void testReverseString() {
        System.out.println(reverseString("hello"));
    }

    // 多分支递归
    /**
     * 大规模问题被分解为：直接量+小规模子问题
     *                 多个小规模子问题
     *      1、找重复:  fib(n) = fib(n-1) + fib(n-2)
     *      2、找变化：
     *      3、找边界： 只有一个字符的时候，不用翻转了，直接返回
     *      递归路径是有分支的
     */
    private int fib(int n) {
        if (n == 1 || n == 2) return 1;
        // n 一直在减小，看减小到什么值时可以得出结果
        // 让两个小弟去做，一直到左边的递归回来，在递归右边的
        /**
         * 计算f(6)
         * 先算f(5) 再算f(4) 再算f(3)
         * 在递归树中，先左，再右，再往回退
         * 先纵深，再横（兄弟），然后回退到父节点
         * 左侧整体先完成，
         */
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 最大公约数，无法分割的，找递推公式
     */
    private int gcd(int m, int n) {
        if (n == 0) return m;
        return gcd(n, m % n);
    }
    @Test
    public void testGcd() {
        System.out.println(gcd(2, 6));
    }

    /**
     * 对数组[0, len-1]的排序
     *      等价于：
     *          对数组的[0, len-2]个元素排序，
     *          然后把len - 1元素插入到这个有序的数组中
     */
    private void insertionSort(int[] arr, int begin , int end) {
        if (begin == end) return;
        insertionSort(arr, 0, end - 1);
        // [begin, end-1]已经排好序了
        // 将arr[end]插入到 [begin, end-1]中
        for (int i = end; i > begin; i--) {
            if (arr[i] < arr[i - 1]) {swap(arr, i, i - 1);}
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // 对前k个元素进行排序
    private void insertSort(int[] arr, int k) {
        if (k == 0) return;
        // 对前k-1个元素进行排序
        insertSort(arr, k - 1);
        int x = arr[k];
        int index = k - 1;
        while (index >= 0 && arr[index] > x) {
            arr[index + 1] = arr[index];
            index--;
        }
        arr[index + 1] = x;
    }
    @Test
    public void testInsertionSort() {
        int[] arr = {3, 2, 4, 1, 7, 6, 9, 5, 0};
        insertionSort(arr, 0, arr.length - 1);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}
