package com.dongfang.leetcode;//给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
//
// 示例 1: 
//
// 输入:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//输出: [1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2: 
//
// 输入:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//输出: [1,2,3,4,8,12,11,10,9,5,6,7]
// 
// Related Topics 数组


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
public class _0054_螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return Collections.emptyList();
        int cols = matrix[0].length;
        int count = rows * cols;
        List<Integer> ans = new ArrayList<>(count);

        int loop = 0;
        while (count > 0) {
            // 上边 左--->右
            for (int col = loop; col < cols - loop && count > 0; col++) {
                ans.add(matrix[loop][col]);
//                System.out.println("上" + matrix[loop][col]);
                count--;
            }
            // 右边
            for (int row = loop + 1; row < rows - loop && count > 0; row++) {
                ans.add(matrix[row][cols - 1 - loop]);
//                System.out.println("右" + matrix[row][cols - 1 - loop]);
                count--;
            }
            // 下边
            for (int col = cols - 2 - loop; col >= loop && count > 0; col--) {
                ans.add(matrix[rows - 1 - loop][col]);
//                System.out.println("下" + matrix[rows - 1 - loop][col]);
                count--;
            }
            // 左边
            for (int row = rows - 2 - loop; row >= loop + 1 && count > 0; row--) {
                ans.add(matrix[row][loop]);
//                System.out.println("左" + matrix[row][loop]);
                count--;
            }
            loop++;
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
