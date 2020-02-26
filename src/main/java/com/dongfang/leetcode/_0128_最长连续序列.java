package com.dongfang.leetcode;//给定一个未排序的整数数组，找出最长连续序列的长度。
//
// 要求算法的时间复杂度为 O(n)。 
//
// 示例: 
//
// 输入: [100, 4, 200, 1, 3, 2]
//输出: 4
//解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。 
// Related Topics 并查集 数组


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
public class _0128_最长连续序列 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // TreeSet中的元素就是有序的
        Set<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;
        List<Integer> list = new ArrayList<>();
        for (Integer num : set) {
            if (set.contains(num + 1)) {
                res++;
            } else {
                list.add(++res);
                res = 0;
            }
        }

        return list.stream().max(Integer::compareTo).get();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
