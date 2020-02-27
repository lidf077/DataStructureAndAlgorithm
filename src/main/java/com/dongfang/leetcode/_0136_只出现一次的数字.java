package com.dongfang.leetcode;//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//
// 说明： 
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
//
// 示例 1: 
//
// 输入: [2,2,1]
//输出: 1
// 
//
// 示例 2: 
//
// 输入: [4,1,2,1,2]
//输出: 4 
// Related Topics 位运算 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
public class _0136_只出现一次的数字 {
    public int singleNumber(int[] nums) {
        // 自反性 `A^B^B = A ^ 0 = A`，连续和同一个因子做异或运算，结果为自己
        // 在序列中连续异或，可以消除重复
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
