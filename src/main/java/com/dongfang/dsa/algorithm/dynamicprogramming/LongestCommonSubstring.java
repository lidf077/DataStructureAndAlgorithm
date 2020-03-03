package com.dongfang.dsa.algorithm.dynamicprogramming;

import org.junit.Test;

/**
 * 最长公共子串（Longest Common SubString)
 * 	1、子串是连续的子序列
 * 	2、求两个字符串的最长公共子串长度
 * 		ABCBA 和 BABCA的最长公共子串是ABC，长度为3
 * 		1）、假设2个子符串分别是str1 str2
 * 				i [1, str1.length]
 * 				j [1, str2.length]
 * 		2）、假设dp(i,j)是以str1[i-1] str2[j-1]结尾的最长公共子串长度
 * 				-- 初始值 dp(i,0) = dp(0,j) = 0
 * 				-- 如果 str1[i-1] = str2[j-1] 那么dp(i,j) = dp(i-1,j-1） + 1
 * 				-- 如果 str1[i-1] ！= str2[j-1] 那么dp(i,j)=0
 * 					最长公子串的长度是所有dp(i,j)中最大的max{dp(i,j)}
 */
public class LongestCommonSubstring {
    @Test
    public void testLcs() {
        String str1 = "ABCBA";
        String str2 = "BABCA";
        int length = longestCommonSubstringOpt(str1, str2);
        System.out.println("length = " + length);
    }

    private int longestCommonSubstring(String str1, String str2) {
        if (str1 == null || str2 == null || str1.isEmpty() || str2.isEmpty()) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int[][] dp = new int[chars1.length + 1][chars2.length + 1];

        int max = 0;
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return max;
    }


    private int longestCommonSubstringOpt(String str1, String str2) {
        if (str1 == null || str2 == null || str1.isEmpty() || str2.isEmpty()) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] rowsChars = chars1, colsChars = chars2;
        if (chars1.length < chars2.length) {
            colsChars = chars1;
            rowsChars = chars2;
        }

        int[] dp = new int[colsChars.length + 1];
        int max = 0;
        for (int row = 1; row <= rowsChars.length; row++) {
            for (int col = colsChars.length; col >= 1; col--) {
                if (chars1[row - 1] != chars2[col - 1]) {
                    dp[col] = 0;
                } else {
                    dp[col] = dp[col - 1] + 1;
                    max = Math.max(dp[col], max);
                }
            }
        }
        return max;
    }
}
