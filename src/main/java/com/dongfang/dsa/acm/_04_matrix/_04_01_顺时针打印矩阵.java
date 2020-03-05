package com.dongfang.dsa.acm._04_matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 一圈一圈地打印，
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 */
public class _04_01_顺时针打印矩阵 {


    public List<Integer> spiralOrder1(int[][] matrix) {
        int rowLen = matrix.length;
        if (rowLen == 0) return Collections.emptyList();
        int colLen = matrix[0].length;
        int count = rowLen * colLen;
        List<Integer> ans = new ArrayList<>(count);

        int loop = 0;
        // 逻辑复杂的题目，变量名要起的有意义
        // 先尝试从一些简单的例子开始
        // 数组类的题目，尤其要注意下标越界
        // 写一步，测一步，debug要逐渐缩小范围
        while (count > 0) {
            // 上边 左--->右
            for (int col = loop; col < colLen - loop && count > 0; col++) {
                ans.add(matrix[loop][col]);
//                System.out.println("上" + matrix[loop][col]);
                count--;
            }
            // 右边
            for (int row = loop + 1; row < rowLen - loop && count > 0; row++) {
                ans.add(matrix[row][colLen - 1 - loop]);
//                System.out.println("右" + matrix[row][colLen - 1 - loop]);
                count--;
            }
            // 下边
            for (int col = colLen - 2 - loop; col >= loop && count > 0; col--) {
                ans.add(matrix[rowLen - 1 - loop][col]);
//                System.out.println("下" + matrix[rowLen - 1 - loop][col]);
                count--;
            }
            // 左边
            for (int row = rowLen - 2 - loop; row >= loop + 1 && count > 0; row--) {
                ans.add(matrix[row][loop]);
//                System.out.println("左" + matrix[row][loop]);
                count--;
            }
            loop++;
        }

        return ans;
    }


    public int[] spiralOrder(int[][] matrix) {
        int rowSize = matrix.length;
        if (rowSize == 0) return new int[]{};
        int colSize = matrix[0].length;
        int count = rowSize * colSize;
        int[] ans = new int[count];

        // loop记录走的圈数，矩阵的坐标跟随圈数发生变化
        int loop = 0;
        int index = 0;
        while (count > 0) {
            // 上
            for (int col = loop; col < colSize - loop && count > 0; col++) {
                ans[index++] = matrix[loop][col];
                count--;
            }
            // 右
            for (int row = loop + 1; row < rowSize - loop && count > 0; row++) {
                ans[index++] = matrix[row][colSize - 1 - loop];
                count--;
            }
            // 下
            for (int col = colSize - 2 - loop; col >= loop && count > 0; col--) {
                ans[index++] = matrix[rowSize - 1 - loop][col];
                count--;
            }
            // 左
            for (int row = rowSize - 2 - loop; row >= loop + 1 && count > 0; row--) {
                ans[index++] = matrix[row][loop];
                count--;
            }
            loop++;
        }

        return ans;
    }
}
