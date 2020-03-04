package com.dongfang.leetcode;//在给定的网格中，每个单元格可以有以下三个值之一：
//
// 
// 值 0 代表空单元格； 
// 值 1 代表新鲜橘子； 
// 值 2 代表腐烂的橘子。 
// 
//
// 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。 
//
// 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[2,1,1],[1,1,0],[0,1,1]]
//输出：4
// 
//
// 示例 2： 
//
// 输入：[[2,1,1],[0,1,1],[1,0,1]]
//输出：-1
//解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
// 
//
// 示例 3： 
//
// 输入：[[0,2]]
//输出：0
//解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length <= 10 
// 1 <= grid[0].length <= 10 
// grid[i][j] 仅为 0、1 或 2 
// 
// Related Topics 广度优先搜索


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
public class _0994_腐烂的橘子 {
    /**
     * 橘子的腐烂过程
     *      1、开始，先将所有的腐烂橘子放入队列中收集起来，并统计新鲜橘子的个数，这是bfs的第一层
     *      2、遍历第一层，取了橘子的坐标，将上下左右的新鲜橘子腐烂，并加入队列，加入完毕后，相当于得到第二层被腐烂的橘子
     *      3、遍历第三层，做相同的操作
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        // 新鲜橘子的数量
        int freshNum = 0;
        Queue<int[]> queue = new LinkedList<>();
        // 统计新鲜橘子的个数
        // 将腐烂的橘子放入队列
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 1) {
                    freshNum++;
                } else if (grid[r][c] == 2) {
                    // 添加完后，队列中是第一层的橘子
                    queue.offer(new int[]{r, c});
                }
            }
        }

        int round = 0;
        // 有新鲜橘子，并且有腐烂橘子，可以进行腐烂这个过程
        while (freshNum > 0 && !queue.isEmpty()) {
            round++;
            int levelSize = queue.size(); // 这个其实也是在一次腐烂过程中被腐烂的橘子个数
            // for循环中就是一轮腐蚀（将上下左右为新鲜的橘子腐烂，并将其坐标加入队列），花费时间为1分钟
            for (int i = 0; i < levelSize; i++) {
                int[] rottedOrange = queue.poll();
                // r [0, row) c [0, col)
                int r = rottedOrange[0], c = rottedOrange[1];
                // 上 (r-1, c)
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    freshNum--;
                    queue.offer(new int[]{r - 1, c});
                }
                // 下 (r+1, c)
                if (r + 1 < row && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    freshNum--;
                    queue.offer(new int[]{r + 1, c});
                }
                // 左 (r, c-1)
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    freshNum--;
                    queue.offer(new int[]{r, c - 1});
                }
                // 右 (r, c+1)
                if (c + 1 < col && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    freshNum--;
                    queue.offer(new int[]{r, c + 1});
                }
            }
        }

        return freshNum > 0 ? -1 : round;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
