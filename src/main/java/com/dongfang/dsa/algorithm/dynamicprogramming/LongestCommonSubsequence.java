package com.dongfang.dsa.algorithm.dynamicprogramming;

import org.junit.Test;

/**
 * 假设两个序列分别是 nums, chars2
 *  i [0, chars1.length]
 *  j [0, chars2.length]
 *
 *  假设dp(i, j)是nums1的前i个元素与nums2的前j个元素的最长公共子序列长度
 *      前i个元素的最后一个元素下标是 i-1
 *      chars1 1, 3, 5, 9, 10
 *      chars2 1, 4, 9, 10
 *      dp(2, 3) = 1
 *          1, 3
 *          1, 4, 9
 *          公共子序列为1
 *       dp(4, 4) = 2
 *       最终求解dp(chars1.length, chars2.length)
 *
 *       初始值 dp(i, 0) = dp(0, j) = 0
 *
 *       dp(i-1, j-1) ---> dp(i, j)由小规模推导出大规模
 *       如果nums1[i-1] == chars2[j-1]，那么dp(i, j) = dp(i-1, j-1) + 1
 *       如果nums1[i-1] != chars2[j-1]， 那么 求nums1的前i个与 nums2的前j-1
 *                                         求nums1的前i-1与nums2的前j个
 *                                         dp(i, j) = max{dp(i-1, j), dp(i, j-1)}
 *                                         [---------][i-1]
 *                                         [---------][j-1]
 *                                         还要看
 *                                            [---------][i-1]
 *                                            [---------]
 *                                            和
 *                                            [---------]
 *                                            [---------][j-1]
 *                                            是否有公共的子序列
 */
@SuppressWarnings("all")
public class LongestCommonSubsequence {

    @Test
    public void testStringLcs() {
        String text1 = "mhunuzqrkzsnidwbun";
        String text2 = "szulspmhwpazoxijwbq";
        int length = longestCommonSubsequence(text1, text2);
        System.out.println("length = " + length);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        if (text1.isEmpty() || text2.isEmpty()) return 0;
        char[] charArray1 = text1.toCharArray();
        char[] charArray2 = text2.toCharArray();
        return longestCommonSubsequenceByDpOptSpaceVersion2(charArray1, charArray2);
//        return lcsHelper(charArray1, charArray1.length, charArray2, charArray2.length);
    }



/*    private <T> int lcsHelper(T[] chars1, int i, T[] chars2, int j) {
        if (i == 0 || j == 0) return 0;
        if (chars1[i - 1].equals(chars2[j - 1])) { // 最后一个元素相等
            return lcsHelper(chars1, i - 1, chars2, j - 1) + 1;
        }
        return Math.max(lcsHelper(chars1, i - 1, chars2, j), lcsHelper(chars1, i, chars2, j - 1));
    }*/

    private int lcsHelper(char[] nums1, int i, char[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) { // 最后一个元素相等
            return lcsHelper(nums1, i - 1, nums2, j - 1) + 1;
        }
        return Math.max(lcsHelper(nums1, i - 1, nums2, j), lcsHelper(nums1, i, nums2, j - 1));
    }

    public int longestCommonSubsequenceByRecursive(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        return lcsHelper(nums1, nums1.length, nums2, nums2.length);
    }

    /**
     * 求nums1 前i个 chars2 前j个元素的最长公共子序列
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @return
     */
    private int lcsHelper(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) { // 最后一个元素相等
            return lcsHelper(nums1, i - 1, nums2, j - 1) + 1;
        }
        return Math.max(lcsHelper(nums1, i - 1, nums2, j), lcsHelper(nums1, i, nums2, j - 1));
    }

    @Test
    public void testLCS() {
        int[] nums1 = {1, 3, 5, 9, 10};
        int[] nums2 = {1, 4, 9, 10};
        int length = longestCommonSubsequenceByRecursive(nums1, nums2);
        System.out.println("length = " + length);
    }

    /**
     * 时间空间复杂度都为 N * M
     * @param nums1
     * @param nums2
     * @return
     */
    private int longestCommonSubsequenceByDp(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        // 数组的初值就是0，没必要再初始化
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        // Arrays.fill(dp[0], 0);

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[nums1.length][nums2.length];
    }


    private int longestCommonSubsequenceByDp(char[] chars1, char[] chars2) {
        if (chars1 == null || chars1.length == 0) return 0;
        if (chars2 == null || chars2.length == 0) return 0;

        // 数组的初值就是0，没必要再初始化
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        // Arrays.fill(dp[0], 0);

        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // dp[i - 1][j] 是上一行的，同一列的
                    // dp[i][j - 1]) 是前一列的，同一行的，都已经计算出来，dp是一行一行计算出来的
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[chars1.length][chars2.length];
    }

    private int longestCommonSubsequenceByDpByRollArray(char[] chars1, char[] chars2) {
        if (chars1 == null || chars1.length == 0) return 0;
        if (chars2 == null || chars2.length == 0) return 0;

        // 数组的初值就是0，没必要再初始化
        int[][] dp = new int[2][chars2.length + 1];
        // Arrays.fill(dp[0], 0);

        for (int i = 1; i <= chars1.length; i++) {
            int row = i % 2;
            int prevRow = (i - 1) % 2;
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[row][j] = dp[prevRow][j - 1] + 1;
                } else {
                    // dp[i - 1][j] 是上一行的，同一列的
                    // dp[i][j - 1]) 是前一列的，同一行的，都已经计算出来，dp是一行一行计算出来的
                    // 用两行就可以计算，当前行，前一行
                    dp[row][j] = Math.max(dp[prevRow][j], dp[row][j - 1]);
                }
            }
        }

        return dp[chars1.length % 2][chars2.length];
    }

    private int longestCommonSubsequenceByDpOptSpace(char[] chars1, char[] chars2) {
        if (chars1 == null || chars1.length == 0) return 0;
        if (chars2 == null || chars2.length == 0) return 0;

        // 数组的初值就是0，没必要再初始化
        int[] dp = new int[chars2.length + 1];
        // Arrays.fill(dp[0], 0);

        int leftTop = 0;

        for (int i = 1; i <= chars1.length; i++) {
            int oldDpJ = 0;
            for (int j = 1; j <= chars2.length; j++) {
                leftTop = oldDpJ;
                oldDpJ = dp[j]; // 计算下一个dp[j + 1]的时候要用旧的dp[j]，因此先存起来
                if (chars1[i - 1] == chars2[j - 1]) {
                    // 前旧位置j的值存起来
                    dp[j] = leftTop + 1;
                } else {
                    // dp[i - 1][j] 是上一行的，同一列的
                    // dp[i][j - 1]) 是前一列的，同一行的，都已经计算出来，dp是一行一行计算出来的
                    // 只用了前一行的数据求下一行
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
            }
        }

        return dp[chars2.length];
    }

    private int longestCommonSubsequenceByDpOptSpaceVersion2(char[] chars1, char[] chars2) {
        if (chars1 == null || chars1.length == 0) return 0;
        if (chars2 == null || chars2.length == 0) return 0;

        // 取行列数中最小的值new数组
        char[] rowNums = chars1, colNums = chars2;
        if (chars1.length < chars2.length) {
            rowNums = chars2;
            colNums = chars1;
        }
        int[] dp = new int[colNums.length + 1];
        // Arrays.fill(dp[0], 0);

        int leftTop = 0;

        for (int i = 1; i <= rowNums.length; i++) {
            int oldDpJ = 0;
            for (int j = 1; j <= colNums.length; j++) {
                leftTop = oldDpJ;
                oldDpJ = dp[j]; // 计算下一个dp[j + 1]的时候要用旧的dp[j]，因此先存起来
                if (rowNums[i - 1] == colNums[j - 1]) {
                    // 前旧位置j的值存起来
                    dp[j] = leftTop + 1;
                } else {
                    // dp[i - 1][j] 是上一行的，同一列的
                    // dp[i][j - 1]) 是前一列的，同一行的，都已经计算出来，dp是一行一行计算出来的
                    // 只用了前一行的数据求下一行
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
            }
        }

        return dp[colNums.length];
    }

    @Test
    public void testLCSByDp() {
        int[] nums1 = {1, 4, 5, 9, 10};
        int[] nums2 = {1, 4, 9, 10};
        int length = longestCommonSubsequenceByDp(nums1, nums2);
        System.out.println("length = " + length);
    }
}