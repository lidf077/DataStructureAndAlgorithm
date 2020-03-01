package com.dongfang.dsa.algorithm.greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * 贪心（Greedy）
 * 	1、贪心策略，也称为贪婪策略
 * 	2、每一步都采取当前状态下最优的选择（局部最优解），从而希望导出全局最优解
 * 	3、应用
 * 			-- 哈夫曼树
 * 			-- 最小生成树算法 Prim Kruskal
 * 			-- 最短路径算法 Dijkstra
 * 	4、问题
 * 		4-1、练习1--最优装载问题（加勒比海盗）
 * 				船的载重W，每件物品的重量为w_i，海盗如何把尺可能多数量的古董装上海盗船
 * 					W 为 30, w_i 为3 5 4 10 7 14 2 11
 * 					贪心策略：每一次都选择重量最小的物品
 *
 * 	    贪心策略并不一定能得到全局最优解
 * 	        因为贪心策略一般没有测试所有可能的解，容易过早做决定，所以没法达到最佳解
 * 	        贪图眼前局部的利益最大化，看不到长远的未来，走一步看一步，鼠目寸光
 *
 * 	        优点：简单，高效，不需要穷举所有可能，通常作为其他算法的辅助算法来使用
 *
 * 	        不从整体上考虑其他可能，每次采用局部最优解，不会再回溯，因此很少情况
 */
public class Greedy {

    /**
     * 练习1--最优装载问题（加勒比海盗）
     * 				船的载重W，每件物品的重量为w_i，海盗如何把尺可能多数量的古董装上海盗船
     * 					W 为 30, w_i 为3 5 4 10 7 14 2 11
     * 					贪心策略：每一次都选择重量最小的物品
     */
    @Test
    public void testPirateLoadProblem() {
        // 每个物品的重量
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        // 选择重量最小的，所以先排序
        Arrays.sort(weights);

        // 船最多载重30
        int capacity = 30;
        // 已经装载的物品的重量
        int weight = 0;
        int count = 0; // 选了多少件物品
        for (int i = 0; i < weights.length && weight < capacity; i++) {
            int newWeight = weight + weights[i]; // 选了新物品后的总重量
            if (newWeight <= capacity) {
                weight = newWeight;
                count++;
                System.out.println("weights[i] = " + weights[i]);
            }
        }
        System.out.println("物品数量为 count = " + count);
    }


    /**
     * 练习2--零钱兑换
     * 				假设有25分、5分、1分的硬币，现在要找给客户41分的零钱，如何办到硬币的个数最少？
     * 					贪心策略：每一次都优先选择面值最大的硬币
     * 						选择25分 剩余16分
     * 						选择10分 剩余6分
     * 						选择5分  剩余1分
     * 						选择一分
     * 						共需要4枚
     * 				最优解是错误的
     * 				face = 25
     *              face = 5
     *              face = 5
     *              face = 5
     *              face = 1
     *              coins = 5
     */
    @Test
    public void testCoinChange() {
        // 硬币面值，可以不断地进行重复选择，优先选择能选的面值里最大的
        Integer[] faces = {25, 20, 5, 1};
        // 传入比较器，从大到小排序
        Arrays.sort(faces, (o1, o2) -> o2 - o1);
        System.out.println("Arrays.toString(faces) = " + Arrays.toString(faces));
        // 要找的钱数，硬币数量
        int money = 41, coins = 0;
        for (Integer face : faces) {
            // 选一枚硬币，不断地重复选择，只要剩余的大于当前面值
            while (money >= face) {
                money -= face;
                coins++;
                System.out.println("face = " + face);
            }
        }
        System.out.println("coins = " + coins);
    }
}
