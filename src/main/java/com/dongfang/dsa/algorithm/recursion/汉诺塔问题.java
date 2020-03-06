package com.dongfang.dsa.algorithm.recursion;

import org.junit.Test;

import java.util.List;
import java.util.Stack;

/**
 * 递归（Recursion） 1、递归：函数（方法）直接或者间接调用自身，是一种常用的编程技巧 f(n) = n + f(n-1) GNU is Not Unix 函数调用完了，栈空间被 回收，也就是弹栈 空间复杂度就是栈的大小，看递归调用多少次，数据规模与栈空间有直接联系 如果递归调用没有终止，将会一直消耗栈空间 最终导致栈内存溢出 Stack Overflow 所以必须有一个明确的结束递归的条件 也叫做边界条件，递归基
 *
 *     1 + 2 + ... + n
 *         return n + sum(n-1)
 *         总消耗时间T(n) = T(n-1) + 1
 *         时间复杂度 O(n)
 *         空间复杂度 O(n)
 *
 *     注意：使用递归不了为了求解最优解，是为了简化解决问题的思路，代码会更简洁
 *     递归求出来的很可能 不是最优解，也有可能是最优解
 * 2、递归的基本思想
 *         -- 拆解问题：将规模大的问题拆解成同类型的规模小的问题
 *         -- 规模较小的问题又不断变成规模更小的问题
 *         -- 规模小到一定程序可以直接得出它的解
 *     求解：
 *         -- 由较小规模问题的解得出较大规模问题的解
 *         -- 由较大规模问题的解不断得出规模更大的问题的解
 *         -- 最后得出原来问题的解
 *     例子：
 *         -- 凡是可以利用上述思想解决问题的，都可以尝试使用递归
 *         -- 很多链表、二叉树的相关问题都可以使用递归来解决
 *             -- 链表、二叉树本身就是递归结构（链表中包含链表，二叉树中包含二叉树）
 * 3、递归的使用套路
 *         -- 明确函数的功能
 *             先不要思考里面的代码怎么写，首先清楚这个函数是做什么的，完成什么功能
 *         -- 明确原问题与子问题的关系
 *             -- 寻找f(n) 与f(n-1)的关系，如何由f(n-1)递推到f(n)
 *         -- 明确递归基（边界条件）规模到达最小的时候，解是什么
 *             -- 递归过程中，子问题的规模在不断减小，当小到一定程度时可以直接得出它的解
 *             -- 寻找递归基：相当于是思考，问题规模小到什么程度的时候可以直接得出解
 * 4、练习
 *     4-1、斐波那契数列
 *             1、1、2、3、5、8、13
 *             F(1) = 1
 *             F(2) = 1
 *             F(n) = F(n-1) + F(n-2) (n>=3) 左边的递归没结束前，右边不会开始，有点类似二叉树的前序遍历
 *             F(n-1)递归到基，栈的深度是n-1，左边被求出来后，栈空间回收，再计算右边的，栈空间在不断被 回收，又重新利用
 *             栈空间是循环利用的
 *             所以递归深度是最多需要多少个栈空间
 *         编写一个函数求第n项斐波那契数列
 *         根据递推式T(N) = T(N-1) + T(N-2) + O(1)，可得到时间复杂度为O(2^N)，每个数都要被 分成两个计算，不断乘2
 *         空间复杂度O(N)
 *         递归调用的空间复杂度 = 递归深度 * 每次递归所需的辅助空间
 *     4-2、上楼梯
 *             假设有n阶台阶，上楼可以一步上1阶，也可以一步上2阶，走完n阶台阶共有多少种不同的走法
 *                 -- 假设n阶台阶有 f(n)种走法，最后一步有2种走法
 *                 -- 如果从n-1阶上1阶，共f(n-1)种走法
 *                 -- 如果从n-2阶上2阶，共f(n-2)种走法
 *                 -- 所以f(n) = f(n-1) + f(n-2)
 *     4-3、汉诺塔 （Hanoi）
 *             编程实现把A的n个盘子移动到C（盘子的编号是[1,n]）
 *                 -- 每次只能移动一个盘子
 *                 -- 大盘子只能在小盘子下面
 *             分两种情况讨论
 *                 当n == 1时，直接将盘子从A移动到C
 *                 当n > 1时，可以拆分成3大步骤
 *                     1）、将n -1 个盘子从A移动到B
 *                     2）、将编号为n的盘子从A移动到C
 *                     3）、将n-1个盘子从B移动到C
 * 5、递归转非递归
 *         递归调用过程中，会将每一次调用的参数，局部变量保存在对应的栈帧中 stack frame
 *                 -- 若递归调用深度较大，会占用比较多的栈空间，甚至会导致栈溢出
 *                 -- 在有些时间，递归会存在大量的重复计算，性能非常差
 *                 -- 这时可以考虑将递归转化为非递归，（递归100%可以转化为非递归）
 *         万能方法
 *                 -- 自己维护一个栈，用来保存参数，局部变量
 *                 -- 但是空间复杂度依然没有得到优化
 *
 *             -- 在某些时候，也可以重复使用一组相同的变量来保存每个栈帧的内容
 *                 重复使用i保存原来栈帧中的参数
 * 6、尾调用（Tail Call）
 *         -- 尾调用：一个函数的最后一个动作是调用函数
 *         -- 尾递归：如果最后一个动作是调用自身，称为尾递归（Tail Recursion），是尾调用的特殊情况
 *                 一些编译器会对尾调用进行优化，以达到节省栈空间的目的
 */
public class 汉诺塔问题 {
    /**
     *                 分两种情况讨论
     *                     当n == 1时，直接将盘子从A移动到C
     *                     当n > 1时，可以拆分成3大步骤
     *                         1）、将n -1 个盘子从A移动到B
     *                         2）、将编号为n的盘子从A移动到C
     *                         3）、将n-1个盘子从B移动到C
     *
     *                 将n2个盘子，从 p1 挪动 到 p3
     *             p2是中间柱子
     */
    public void hanoi(int n, String p1, String p2, String p3) {
        if (n == 1) {
            move(n, p1, p3);
            return;
        }
        hanoi(n - 1, p1, p3, p2);
        move(n, p1, p3);
        hanoi(n - 1, p2, p1, p3);
    }

    private void move(int no, String from, String to) {
        System.out.println("将 " + no + " 号盘子从 " + from + " 移到 " + to);
    }

    @Test
    public void testHanoi() {
        hanoi(3, "A", "B", "C");
    }




    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        C.addAll(A);
    }


    private void log(int n) {
        if (n < 1) return;
        log(n - 1);
        int v = n + 10;
        System.out.println(v);
    }

    private static class Frame {
        int n;
        int v;

        public Frame(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }

    /**
     * 模拟函数调用栈
     * @param n
     */
    private void logStack(int n) {
        Stack<Frame> frames = new Stack<>();
        while (n > 0) {
            frames.push(new Frame(n, n + 10));
            n--;
        }

        while (!frames.isEmpty()) {
            Frame frame = frames.pop();
            System.out.println(frame.v);
        }
    }

    private void logIterate(int n) {
        for (int i = 0; i <= n; i++) {
            System.out.println(i + 10);
        }
    }

    @Test
    public void convertRecursionToIterator() {
        logStack(6);
    }
}