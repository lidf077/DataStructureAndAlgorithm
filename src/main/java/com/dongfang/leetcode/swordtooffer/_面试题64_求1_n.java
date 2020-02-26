package com.dongfang.leetcode.swordtooffer;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 利用 && 和 || 的短路特性
 *
 * 对于A && B，如果A为假，那么就不执行B了;而如果A为真，就会执行B。
 * 对于A || B，如果A为真，那么就会不执行B了;而如果A为假，就会执行B。
 */
public class _面试题64_求1_n {
    public int sumNums(int n) {
        int sum = n;
        // && 短路与 n > 0为假时，不会再进行后面的判断，递归终止
        boolean res = (n > 0) && ((sum += sumNums(n - 1)) > 0);
        return sum;
    }
}
