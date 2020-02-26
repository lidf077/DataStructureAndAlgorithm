package com.dongfang.leetcode.swordtooffer;//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
//判断数组中是否含有该整数。 
//
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// [
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
//
// 
//
// 限制： 
//
// 0 <= n <= 1000 
//
// 0 <= m <= 1000 
//
// 
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/ 
// Related Topics 数组 双指针


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _面试题04_二维数组中的查找 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;

        int cols = matrix[0].length;
        if (cols == 0) return false;

        // 右上角
        int x_row = 0;
        int y_col = cols - 1;

        while (x_row < rows && y_col >= 0) {
            if (matrix[x_row][y_col] > target) {
                y_col--;
            } else if (matrix[x_row][y_col] < target) {
                x_row++;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            if (binarySearch(ints, target)) return true;
        }
        return false;
    }

    private boolean binarySearch(int[] arr, int target) {
        int begin = 0, end = arr.length;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target < arr[mid]) {
                end = mid;
            } else if (target > arr[mid]) {
                begin = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public boolean findNumberIn2DArray1(int[][] matrix, int target) {
        Set<Integer> set = new HashSet<>();
        for (int[] ints : matrix) {
            set.addAll(Arrays.stream(ints).boxed().collect(Collectors.toList()));
        }

        return set.contains(target);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
