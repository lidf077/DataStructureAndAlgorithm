package com.dongfang.leetcode;//给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
//
// 示例: 
//
// 输入: 3
//输出:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//] 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
public class _0059_螺旋矩阵II {
    public int[][] generateMatrix(int n) {
        if (n <= 0) return new int[][]{};
        int[][] matrix = new int[n][n];
        int rows = n;
        int cols = n;
        int count = n * n;


        // loop记录走的圈数，矩阵的坐标跟随圈数发生变化
        int loop = 0;
        int index = 1;
        while (count > 0) {
            // 上
            for (int col = loop; col < cols - loop && count > 0; col++) {
                matrix[loop][col] = index++;
                count--;
//                System.out.println("上 count " + count);
            }
            // 右
            for (int row = loop + 1; row < rows - loop && count > 0; row++) {
                matrix[row][cols - 1 - loop] = index++;
                count--;
//                System.out.println("右 count " + count);
            }
            // 下
            for (int col = cols - 2 - loop; col >= loop && count > 0; col--) {
                matrix[rows - 1 - loop][col] = index++;
                count--;
//                System.out.println("下 count " + count);
            }
            // 左
            for (int row = rows - 2 - loop; row >= loop + 1 && count > 0; row--) {
                matrix[row][loop] = index++;
                count--;
//                System.out.println("左 count " + count);
            }
            loop++;
//            System.out.println("loop = " + loop);
//            System.out.println("count = " + count);
        }

        return matrix;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
