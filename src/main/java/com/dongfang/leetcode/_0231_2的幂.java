package com.dongfang.leetcode;//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学


//leetcode submit region begin(Prohibit modification and deletion)
public class _0231_2的幂 {
    /**
     *      10100 - 1
     *      10011
     *     &10000
     *      (n - 1) & n 每次这样的操作，消除掉一个1
     *      可以消除多少次，就证明有多少个1
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return ((n - 1) & n) == 0;
    }

    public boolean isPowerOfTwo1(int n) {
        if (n < 0) return false;

        return hammingWeight(n) == 1;
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += ((n >> i) & 1);
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
