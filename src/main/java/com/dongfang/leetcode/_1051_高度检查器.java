package com.dongfang.leetcode;//学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
//
// 请你返回能让所有学生以 非递减 高度排列的最小必要移动人数。 
//
// 
//
// 示例： 
//
// 输入：heights = [1,1,4,2,1,3]
//输出：3 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <= 100 
// 1 <= heights[i] <= 100 
// 
// Related Topics 数组

// https://leetcode-cn.com/problems/height-checker/
//leetcode submit region begin(Prohibit modification and deletion)
public class _1051_高度检查器 {
    public int heightChecker(int[] heights) {
        // 值的范围是1 <= heights[i] <= 100，因此需要1,2,3,...,99,100，共101个桶
        int[] arr = new int[101];
        // 遍历数组heights，计算每个桶中有多少个元素，也就是数组heights中有多少个1，多少个2，。。。，多少个100
        // 将这101个桶中的元素，一个一个桶地取出来，元素就是有序的
        for (int height : heights) {
            arr[height]++;
        }

        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            // arr[i]，i就是桶中存放的元素的值，arr[i]是元素的个数
            // arr[i]-- 就是每次取出一个，一直取到没有元素，成为空桶
            while (arr[i]-- > 0) {
                // 从桶中取出元素时，元素的排列顺序就是非递减的，然后与heights中的元素比较，如果不同，计算器就加1
                if (heights[j++] != i) count++;
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
