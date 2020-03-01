package com.dongfang.leetcode;//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4],
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
public class _0053_最大子序和 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int maxSubSum = Integer.MIN_VALUE;
        // begin 固定，end一直挪动，单个元素也是子序列
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                int subSum = sumOfSubArray(nums, begin, end);
                if (subSum > maxSubSum) maxSubSum = subSum;
            }
        }
        System.out.println("maxSubSum = " + maxSubSum);
        return maxSubSum;
    }

    private int sumOfSubArray(int[] nums, int begin, int end) {
        int sum = 0;
        for (int i = begin; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
