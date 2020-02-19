package com.dongfang.leetcode;//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
//


//leetcode submit region begin(Prohibit modification and deletion)
public class _面试题51_数组中的逆序对 {
    public int reversePairs(int[] nums) {
        int len = nums.length;
        int count = 0;
        for (int begin = 1; begin < len; begin++) {
            for (int cur = begin; cur > 0; cur--) {
                // 小于才交换，所以是稳定的
                if (nums[cur] < nums[cur - 1]) {
                    count++;
                    swap(nums, cur, cur - 1);
                }
            }
        }
        return count;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
