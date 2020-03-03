package com.dongfang.dsa.algorithm.dynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

/**
 * 练习3--0-0背包
 * 				有n件物品和一个最大承重为W的背包，每件物品的重量是w_i 价值是v_i
 * 					在保证总重量不超过W的前提下，将哪几件物品装入背包，可以使得背包的总价值最大
 * 					注意：每个物品只有1件，也就每个物品只能选择0件或者1件，因此称为0-1背包问题
 *假设values是价值数组，weights是重量数组
 * 	1、编号为k的物品，价值是values[k] 重量是weights[k] k [0,n)
 * 	        int[] values = {6, 3, 5, 4, 6};
 * 			int[] weights = {2, 2, 6, 5, 4};
 * 			int capacity = 10;
 * 	2、假设dp(i,j)是最大承重为j，有前i件物品可选时的最大总价值， i [0,n] j [0,W]
 * 			dp(3, 7)是最大承重为7，有前3件物品可选时的最大价值
 * 			6, 3, 5
 * 			2, 2, 6,
 * 			7
 * 	3、初始值
 * 			dp(i, 0)总重量为0，所以dp(i, 0) = 0
 * 			dp(0, j)无物品可选，dp(0, j) = 0
 * 	4、状态方程
 * 			如果j < weights[i-1] 背包最大重量小于物品重量，无法装了 dp(i,j) = dp(i-1, j)
 * 			否则
 * 			 dp(i -1, j) ---> dp(i, j)
 * 			 从i-1到i，取决于是否选择第i件物品
 * 				不选第i件 dp(i, j) = dp(i-1, j) 有i - 1件可选，最大称重不是j
 * 				选择第i件 dp(i, j) = values[i] + dp(i-1, j - weights[i]) 剩下i-1件物品的最大重量是j-weights[i]
 * 				在两者之间取最大值
 * 	3、最终的解dp(n, capacity)
 */


@SuppressWarnings("all")
public class Knapsack {
    @Test
    public void testMaxValue() {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 1;
        int value = maxValueExactOptSpaceAndLoop(values, weights, capacity);
        System.out.println("value = " + value);
    }

    private int maxValue(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 第i件物品的选择
                // 重量j < 第i件物品的重量，无法选择
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return dp[weights.length][capacity];
    }

    private int maxValueOptSpace(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= 1; j--) {
                // 第i件物品的选择
                // 重量j < 第i件物品的重量，无法选择
                if (j < weights[i - 1]) continue;
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity];
    }


    private int maxValueOptSpaceAndLoop(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {
                // 第i件物品的选择
                // 重量j < 第i件物品的重量，无法选择
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity];
    }


    /**
     * 0-1背包--恰好装满
     * 	1、总重量恰好为W，而不是不超过W，求最大价值
     * 	2、dp(i,j)初始状态调整
     * 	3、dp(i,0) 总重量恰好为0，最大总价值也必然为0
     * 	4、dp(0,j)=Integer.Min_Value 负无穷，j>=1 负数在这里代表恰好无法装满
     * @param values
     * @param weights
     * @param capacity
     * @return
     */
    private int maxValueExactOptSpaceAndLoop(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
//        for (int j = 1; j <= capacity; j++) {
//            dp[j] = Integer.MIN_VALUE;
//        }
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {
                // 如果能够凑够，dp[j]会大于0
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }
}
