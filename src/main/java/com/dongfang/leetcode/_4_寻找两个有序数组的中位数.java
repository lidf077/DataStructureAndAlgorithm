package com.dongfang.leetcode;//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
//
// 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class _4_寻找两个有序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int joinLen = nums1.length + nums2.length;
        int[] join = new int[joinLen];
        int i = 0, j = 0;
        int k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                join[k] = nums1[i];
                i++;
            } else {
                join[k] = nums2[j];
                j++;
            }
            k++;
        }

        while (i < nums1.length) {
            join[k] = nums1[i];
            i++;
            k++;
        }

        while (j < nums2.length) {
            join[k] = nums2[j];
            j++;
            k++;
        }
        System.out.println("Arrays.toString(join) = " + Arrays.toString(join));
        int halfLen = joinLen / 2;
        System.out.println("halfLen = " + halfLen);
        return join.length % 2 == 0 ? (join[halfLen - 1] + join[halfLen]) / 2. : join[halfLen];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
