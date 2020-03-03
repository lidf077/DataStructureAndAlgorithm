package com.dongfang.leetcode;//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
public class _0070_爬楼梯 {

    /**
     * 动态规划
     * 算法
     *
     * 不难发现，这个问题可以被分解为一些包含最优子结构的子问题，即它的最优解可以从其子问题的最优解来有效地构建，我们可以使用动态规划来解决这一问题。
     *
     * 第 ii 阶可以由以下两种方法得到：
     *
     * 在第 (i-1)(i−1) 阶后向上爬一阶。
     *
     * 在第 (i-2)(i−2) 阶后向上爬 22 阶。
     *
     * 所以到达第 ii 阶的方法总数就是到第 (i-1)(i−1) 阶和第 (i-2)(i−2) 阶的方法数之和。
     *
     * 令 dp[i]dp[i] 表示能到达第 ii 阶的方法总数：
     *
     * dp[i]=dp[i-1]+dp[i-2]
     * dp[i]=dp[i−1]+dp[i−2]
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int one = 1; // n = 1的初值
        int two = 2; // n = 2的初值
        int three = 0;

        for (int i = 3; i <= n; i++) {
            three = one + two;
            one = two;
            two = three; // two才是算到n的值
        }
        return two;
    }


    public int climbStairs1(int n) {
        int[] dp = new int[n + 1];
        // dp(i) = dp(i - 1) + dp(i - 2)
        // dp(0) = 0
        dp[0] = 1;
        // dp(1) = 1
        dp[1] = 1;
        // dp(2) = 2
//        dp[2] = 2;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
