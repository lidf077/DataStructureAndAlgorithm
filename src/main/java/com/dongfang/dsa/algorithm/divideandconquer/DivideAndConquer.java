package com.dongfang.dsa.algorithm.divideandconquer;

import org.junit.Test;

public class DivideAndConquer {
    private int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

    // n^3
    @Test
    public void testMaxSubArrayByBruteForce() {
        if (arr == null || arr.length == 0) return;
        int maxSubSum = Integer.MIN_VALUE;
        // begin 固定，end一直挪动，单个元素也是子序列
        for (int begin = 0; begin < arr.length; begin++) {
            for (int end = begin; end < arr.length; end++) {
                int subSum = sumOfSubArray(arr, begin, end);
                if (subSum > maxSubSum) maxSubSum = subSum;
            }
        }
        System.out.println("maxSubSum = " + maxSubSum);
    }

    private int sumOfSubArray(int[] nums, int begin, int end) {
        int sum = 0;
        for (int i = begin; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }


    // N^2
    @Test
    public void testMaxSubArrayByBruteForceOpt() {
        if (arr == null || arr.length == 0) return;
        int maxSubSum = Integer.MIN_VALUE;
        // begin 固定，end一直挪动，单个元素也是子序列
        for (int begin = 0; begin < arr.length; begin++) {
            int subSum = 0;
            // 每一人优化点，不要重复求和，新的各只要前一次求和加上nums[end]就可以
            for (int end = begin; end < arr.length; end++) {
                subSum += arr[end];
                maxSubSum = Math.max(subSum, maxSubSum);
            }
        }
        System.out.println("maxSubSum = " + maxSubSum);
    }

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



}
