package com.dongfang.leetcode;//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
//
// 说明： 
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
//
// 示例 1: 
//
// 输入: [2,2,3,2]
//输出: 3
// 
//
// 示例 2: 
//
// 输入: [0,1,0,1,0,1,99]
//输出: 99 
// Related Topics 位运算


//leetcode submit region begin(Prohibit modification and deletion)
public class _0137_只出现一次的数字II {
    // 先想最简单的做法
    public int singleNumber(int[] nums) {

        int res = 0;
        // 将num中所有数的每一位加起来，对3求余，余数就是剩下的一个数的二进制位，然后对结果一直左移
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                sum = sum + ((num >> i) & 1);
            }
            res = res | ((sum % 3) << i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
