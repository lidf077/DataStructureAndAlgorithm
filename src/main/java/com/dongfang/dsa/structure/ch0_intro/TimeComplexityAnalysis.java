package com.dongfang.dsa.structure.ch0_intro;

import org.junit.Test;

/**
 * 如何评价一个算法的好坏
 *      1、比较不同算法对同一组输入的运行时间，此方法为事后统计
 *          严重依赖于硬件和运行时不确定的环境因素（os资源的分配情况）
 *          必须要编写相应的测试代码
 *          对于不同的输入，两个算法可能有不同的表现
 *          测试数据难以保证测试的公正性
 *
 *     2、对不合的输入的反应和处理能力，入参的边界处理
 *          时间复杂度：估算程序中的指令的执行行数
 *                  每一句指令的执行时间一样，然后计算执行了多少指令
 *          空间复杂度：程序运行时要使用的内存空间
 *
 *          大O表示法就是估算（实际的算法实现时，还是会考虑低阶项，常数项，参见jdk的排序）
 *              忽略 常数、系数、低阶项
 *
 *              仅是一种粗略的分析模型，是一种估算，短时间内帮助我们了解一个算法
 *
 *              对数阶的细节
 *                  log2(n) = log2(9) * log9(n)
 *                  换底公式，任何底数都可以转换为log2(n)
 *                  因此log2(n) log9(n)统称为log(n)
 *
 *          O(1)       常数阶
 *          O(N)       线性阶
 *          O(N^2)     平方阶
 *          O(logN)    对数阶
 *          O(NlogN)   NlogN阶
 *          O(n^3)     立方阶
 *          O(2^N)     指数阶
 */
public class TimeComplexityAnalysis {
    /**
     * 算法复杂度计算案例
     */
    @Test
    public void test001(int n) {
        // 1 只走一条分支，所以只执行一条指令
        // 判断一条，打印一条，判断一般不管，算不算影响不大
        if (n > 10) {
            System.out.println("n > 10");
        } else if (n > 5) {
            System.out.println("n > 5");
        } else {
            System.out.println("n <= 5");
        }

        // i = 0
        // i < 4  0<4 1<4     2<4     3<4     4<4
        // i++        0++=1   1++=2   2++=3   3++=4
        // 赋值 1条
        // 判断 5条
        // 自增 4条
        // 打印 4次
        // 1 + 5 + 4 + 4 = 14
        // O(14) = O(1)

        for (int i = 0; i < 4; i++) {
            System.out.println("test");
        }
    }

    @Test
    public void test002(int n) {
        // 1 + n + n + n
        // 1 + 3n
        // O(n)
        for (int i = 0; i < n; i++) {
            System.out.println("test");
        }
    }

    @Test
    public void test003(int n) {
        // 1 + 2n + n(1 + 3n)
        // 3n^2 + 3n + 1
        // O(n^2)
        for (int i = 0; i < n; i++) {
            // 1 + 3n
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }

    @Test
    public void test004(int n) {
        // 1 + 2n + n(1 + 45)
        // 48n + 1
        // O(n)
        for (int i = 0; i < n; i++) {
            // 1 + 15 + 15 + 15
            for (int j = 0; j < 15; j++) {
                System.out.println("test");
            }
        }
    }

    @Test
    public void test005(int n) {
        // n能除以多少次了，循环执行多少次
        // 8 4 2 1 0 执行三次
        // 2^3 = 8
        // 2^k = n
        // k = log2(n) 执行次数
        // log(n)
        while ((n = n/ 2) > 0) {
            System.out.println("test");
        }
    }

    @Test
    public void test006(int n) {
        // log5(n)
        // log(n)
        while ((n /= 5) > 0) {
            System.out.println("test");
        }
    }

    @Test
    public void test007(int n) {
        // i = 1 2 4 8 16 乘多少次到达n
        // 2^k >n
        // k = log2(n)
        // 1 + 2log2(n) +  (1 + 3n)log2(n)
        // 2nlog2(n) + 2log2(n) + 1
        // O(nlog(n))
        for (int i = 1; i < n; i = 2 * i) {
            // 1 + 3n
            for (int j = 0; j < n; j++) {
                System.out.println("test");
            }
        }
    }
}
