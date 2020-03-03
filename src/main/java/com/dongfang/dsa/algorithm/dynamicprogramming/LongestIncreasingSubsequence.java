package com.dongfang.dsa.algorithm.dynamicprogramming;

import org.junit.Test;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@SuppressWarnings("all")
public class LongestIncreasingSubsequence {

    /**
     * 最长上升子序列
     * 	1、状态定义
     * 			-- 假设数组是nums, [10,9,2,5,3,7,101,18]
     * 			-- dp(i)是以nums[i]结尾的最长上升子序列的长度，初始值为1 [0, nums.length)
     * 					以nums[0] 10 结尾的最长上升子序列是 {10} dp(0) = 1
     * 					以nums[1]  2 结尾的最长上升子序列是 {2}  dp(1) = 1
     * 					以nums[2]  2 结尾的最长上升子序列是 {2}  dp(2) = 1
     * 					以nums[3]  5 结尾的最长上升子序列是 {2, 5}  dp(3) = dp(2) + 1
     *
     * 				遍历 j [0, i)
     * 					当 nums[i] > num[j]
     * 						nums[i]可以接在nums[j]后面，形成一个比dp(j)更长的上升子序列，长度为 dp(j) + 1
     * 						 dp(i) = max{dp(i), dp(j) + 1}
     * 					当nums[i] <= nums[j]
     * 						nums[i] 不能接在nums[j]后面，跳过遍历
     *      空间 O(n)   空间无法优化，因为计算时要用到前面所有的dp值
     *      时间 O(n^2)
     * @param nums
     * @return
     */
    // 状态压缩
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};

    @Test
    public void testLengthOfLIS() {
        int lengthOfLIS = lengthOfLIS(arr);
        System.out.println("lengthOfLIS = " + lengthOfLIS);
    }
}
