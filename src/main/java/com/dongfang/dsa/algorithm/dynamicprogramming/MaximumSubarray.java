package com.dongfang.dsa.algorithm.dynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

@SuppressWarnings("all")
public class MaximumSubarray {
    private int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

    @Test
    public void testMaxSubArrayByDivideAndConquer() {
        int maxSubSum = maxSubArray(arr);
        System.out.println("maxSubSum = " + maxSubSum);
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray(nums, 0, nums.length);
    }

    /**
     * 	解法2、分治
     * 			1、将序列均匀地分成2个子序列 [begin, end) [begin, mid) [mid,end) mid = (begin + end)>>1
     * 			2、假设问题的解是S[i,j)，那么问题的解有3种可能
     * 				2-1、[i,j)存在于[begin, mid)    也是左边的最大连续子序列和
     * 				2-2、[i,j)存在于[mid, end)
     * 				2-3、[i,j)一部分存在于[begin, mid) 另一部分存在于[mid, end)
     * 					[i, j) = [i, mid) + [mid, j)，以mid为界线，求左边和右边最大。加起来
     * 				从3个值中求出最大值
     * @param nums
     * @param begin
     * @param end
     * @return
     */
    // [begin, end)区间内最大连续子序列的和  NlogN
    private int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;

//        // [begin,mid)区间内最大连续子序列的和
//        int leftMax = maxSubArray(nums, begin, mid);
//        // [mid,end)区间内最大连续子序列的和
//        int rightMax = maxSubArray(nums, mid, end);


        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin ; i--) {
            leftSum += nums[i];
            // 所有元素加完，就知道哪个最大了
            leftMax = Math.max(leftMax, leftSum);
        }
        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        int max = leftMax + rightMax;

        // 结合左右两边的最大值 左边最大值  右边最大值
        return Math.max(max,
                Math.max(
                        maxSubArray(nums, begin, mid),
                        maxSubArray(nums, mid, end)));
    }


    /**
     * 最大连续子序列和
     * 	1、给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 	2、动态规划策略
     * 		-- 状态定义
     * 				假设dp(i)是以nums[i]结尾的最大连续子序列和（nums是整个序列）
     * 					[-2,1,-3,4,-1,2,1,-5,4]
     * 					以nums[0] -2结尾的最大连续子序列是 -2 所以dp(0) = -2
     * 					以nums[1] 1 结尾的最大连续子序列是  1 所在dp(1) = 1  {-2, 1} {1}
     * 					以nums[2] -3结尾的最大连续子序列是1 -3，所在dp(2) = dp(1) + -3 = -2
     * 					以nums[3] 4 结尾的最大连续子序列是4，  所在dp(3) = 4
     * 					以nums[4] -1结尾的最大连续子序列是4 -1，所在dp(4) = dp(3) + -1 = 3
     * 	3、状态转移方程
     * 		-- 如果dp(i-1) <= 0，那么dp(i) = nums[i]
     * 		-- 如果dp(i-1) >  0，那么dp(i) = dp(i-1) + nums[i]
     * 	4、初始状态
     * 		-- dp(0) = nums[o]
     * 	5、最终的解
     * 		-- 最大的连续子序列和是所有的dp(i)中的最大值max{dp(i)}
     * @param nums
     * @return
     */
    public int maxSubArrayByDynamicProgramming(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // dp[i]是以nums[i]结尾的连续子序列中和最大的，
            // 以nums[i]结尾的连续子数组在两个
            // 以nums[i-1]结尾的+nums[i]
            // nums[i]
            // 取这两个的最大值
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            if (dp[i] > max) max = dp[i];
        }
        // 以nums[i]结尾的最大连续子序列和
        //Arrays.toString(dp) = [-2, 1, -2, 4, 3, 5, 6, 1, 5]
        System.out.println("Arrays.toString(dp) = " + Arrays.toString(dp));
        return max;
    }


    // 求dp[i] 的时候，只用到了dp[i-1]前一个值，因此只保存前一个值就可以了，没必要保存所有的值
    // 时间空间复杂度 O(N)，空间 O(1)
    public int maxSubArrayByDynamicProgrammingOptSpaceComplexity(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(nums[i], dp + nums[i]);
            if (dp > max) max = dp;
        }
        return max;
    }


    public int maxSubArrayByDynamicProgrammingOpt(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    @Test
    public void testMaxSubArrayByDynamicProgramming() {
        int max = maxSubArrayByDynamicProgrammingOptSpaceComplexity(arr);
        System.out.println("max = " + max);
    }

}
