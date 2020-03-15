package com.dongfang.leetcode;//给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的
//四个边缘都被水包围着。 
//
// 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。) 
//
// 示例 1: 
//
// 
//[[0,0,1,0,0,0,0,1,0,0,0,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,1,1,0,1,0,0,0,0,0,0,0,0],
// [0,1,0,0,1,1,0,0,1,0,1,0,0],
// [0,1,0,0,1,1,0,0,1,1,1,0,0],
// [0,0,0,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,0,0,0,0,0,0,1,1,0,0,0,0]]
// 
//
// 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。 
//
// 示例 2: 
//
// 
//[[0,0,0,0,0,0,0,0]] 
//
// 对于上面这个给定的矩阵, 返回 0。 
//
// 注意: 给定的矩阵grid 的长度和宽度都不超过 50。 
// Related Topics 深度优先搜索 数组


//leetcode submit region begin(Prohibit modification and deletion)
public class _0695_岛屿的最大面积 {
    public int maxAreaOfIsland(int[][] grid) {
        int[][] cloneGrid = grid.clone();

        int maxArea = 0;
        for (int i = 0; i < cloneGrid.length; i++) {
            for (int j = 0; j < cloneGrid[i].length; j++) {
                if (cloneGrid[i][j] == 1)
                    maxArea = Math.max(maxArea, areaAroundOf(i, j, cloneGrid));
            }
        }

        return maxArea;
    }

    // 使用dfs求坐标 (x,y)附近的岛屿的面积
    private int areaAroundOf(int x, int y, int[][] grid) {
        // corner case
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || grid[x][y] == 0) return 0;
        grid[x][y] = 0;
        int area = 1;
        // 上
        area += areaAroundOf(x, y - 1, grid);
        // 下
        area += areaAroundOf(x, y + 1, grid);
        // 左
        area += areaAroundOf(x - 1, y, grid);
        // 右
        area += areaAroundOf(x + 1, y, grid);

        return area;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
