package com.dongfang.dsa.acm._04_matrix;

import javax.swing.text.StyledEditorKit;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 数组最重要的是下标越界
 */
public class _04_02_将0所在行列清零 {

    // 这个题，不管怎么做，都是要记录 0 的坐标的
    // 或者是记录某一行或者某一列中是不是有 0
    // 如果记录有的话，就全部归0
    // 其实记录 0 的坐标有点繁琐，标记一行一列是否有0倒是比较简单
    public void setZeroes(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        // 思路，不使用额外的空间来记录行列中是否有0，而是用数组中的上边和左边两个一维数组来标记
        boolean firstRowContainZero = false; // 检查第一行中是否有0
        for (int col = 0; col < colLen; col++) {
            if (matrix[0][col] == 0) {
                firstRowContainZero = true;
                break;
            }
        }
        boolean firstColumnContainZero = false;
        for (int row = 0; row < rowLen; row++) { // 检查第一列中是否有0
            if (matrix[row][0] == 0) {
                firstColumnContainZero = true;
                break;
            }
        }

        // 第一行第一列中是否有0已经得知了
        // 从 1， rowLen - 1 行中，是否有0
        // 从 1， colLen - 1 列中，是否有0
        for (int row = 1; row < rowLen; row++) {
            for (int column = 1; column < colLen; column++) {
                if (matrix[row][column] == 0) { // 发现了0，在第一行第一列中标记
                    matrix[0][column] = 0; // 在第一行进行标记
                    matrix[row][0] = 0;    // 在第一列进行标记
                }
            }
        }

        // 从 1， rowLen - 1 行中，有标记，则将元素置0
        // 从 1， colLen - 1 列中，有标记，置0
        for (int row = 1; row < rowLen; row++) {
            for (int column = 1; column < colLen; column++) {
                if (matrix[0][column] == 0 || matrix[row][0] == 0) {
                    matrix[row][column] = 0;
                }
            }
        }

        if (firstRowContainZero) {
            for (int column = 0; column < colLen; column++) {
                matrix[0][column] = 0;
            }
        }
        if (firstColumnContainZero) {
            for (int row = 0; row < rowLen; row++) {
                matrix[row][0] = 0;
            }
        }
    }

    // 直接使用辅助空间的话，记录0元素所在的位置
    // 下面这个做法是使用bfs，先记录0的下标，然后将下标一个一个地出队，然后将所在行列置为0
    public void setZeroesByBfs(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (matrix[i][j] == 0) queue.offer(new int[]{i, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] positions = queue.poll();
            int r = positions[0];
            int c = positions[1];

            for (int j = 0; j < colLen; j++) {
                matrix[r][j] = 0;
            }
            for (int i = 0; i < rowLen; i++) {
                matrix[i][c] = 0;
            }
        }
    }
}
