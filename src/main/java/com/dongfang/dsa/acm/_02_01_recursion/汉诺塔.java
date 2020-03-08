package com.dongfang.dsa.acm._02_01_recursion;

import org.junit.Test;

public class 汉诺塔 {

    /**
     *
     * -
     * --
     * ---
     * ----
     * -----
     * A               B             C
     * 三个柱子，将A上面的盘子移动到B，C是辅助
     * 1、[1, n-1]移动到C, [n]移动到B
     * 2、如下图所示，B上的柱子不用动了，跟开始的情况一样了，A成了辅助，转换柱子的角色
     *
     * -
     * --
     * ---
     * ----        -----
     * C              B             A
     */

    /**
     * 将N个盘子从source移动到target的路径的打印
     *
     * @param N      初始的N个从小到达的盘子，N是最大编号
     * @param from 原始柱子
     * @param to 辅助的柱子
     * @param helper   目标柱子
     */
    private void printHanoiTower(int N, String from, String to, String helper) {
        if (N == 1) { // 只有一个盘子，三个柱子
            System.out.println("move " + N + " from " + from + " to " + to);
            return;
        }

        printHanoiTower(N - 1, from, helper, to); // 先把前N-1个盘子挪到辅助空间上去
        System.out.println("move " + N + " from " + from + " to " + to);  // N可以顺利到达target
        printHanoiTower(N - 1, helper, to, from); // 让N-1从辅助空间回到源空间上去
    }

    @Test
    public void testHanoiTower() {
        printHanoiTower(3, "A", "B", "C");
    }
}
