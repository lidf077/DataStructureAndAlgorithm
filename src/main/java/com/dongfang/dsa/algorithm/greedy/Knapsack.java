package com.dongfang.dsa.algorithm.greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 练习3--0-0背包
 * 				有n件物品和一个最大承重为W的背包，每件物品的重量是w_i 价值是v_i
 * 					在保证总重量不超过W的前提下，将哪几件物品装入背包，可以使得背包的总价值最大
 * 					注意：每个物品只有1件，也就每个物品只能选择0件或者1件，因此称为0-1背包问题
 *
 * 				如果采取贪心策略，有3个方案
 * 					1、价值主导：优先选择价值最高的物品放进背包  找零钱方式
 * 					2、重量主导：优先选择重量最轻的物品放进背包  装物品方式
 * 					3、价值密度主导：优先选择价值密度最高的物品放进背包（价值密度 = 价值 /  重量）
 * 						假设背包最大承重150，7个物品如表格所示
 * 						编号 1		2		3		4		5		6		7
 * 						重量 35		30		60		50		40		10		25
 * 						价值 10		40		30		50		35		40		30
 * 						密度 0.29	1.33	0.5		1.0		0.88	4.0		1.2
 * 						1、价值主导： 放入背包的物品编号是 4 2 6 5 总重量130 总价值165
 * 						2、重量最轻主导： 放入背包的物品编号是 6 7 2 1 5 总重量140 总价值155
 * 						3、价值密度主导：放入背包的物品编号是 6 2 7 4 1 总重量150 总价值170
 */

public class Knapsack {
    private Article[] articles = new Article[]{
            new Article(35, 10), new Article(30, 40),
            new Article(60, 30), new Article(50, 50),
            new Article(40, 35), new Article(10, 40),
            new Article(25, 30)
    };

    private void greedyAlgorithm(Comparator<Article> comparator) {
        Arrays.sort(articles, comparator);
        System.out.println("Arrays.deepToString(articles) = " + Arrays.deepToString(articles));
        // 背包可容纳的重量
        int capacity = 150;
        // 选择物品后，背包的总重量
        int weight = 0;
        // 选择物品后，背包的总价值
        int value = 0;
        List<Article> selectedArticles = new ArrayList<>();
        for (int i = 0; i < articles.length && weight < capacity; i++) {
            int newWeight = weight + articles[i].getWeight();
            if (newWeight <= capacity) {
                weight = newWeight;
                value += articles[i].getValue();
                selectedArticles.add(articles[i]);
            }
        }
        System.out.println("value = " + value);
        System.out.println("weight = " + weight);
        System.out.println("selectedArticles");
        selectedArticles.forEach(System.out::println);
    }

    @Test
    public void testValueOrientated() {
        System.out.println("testValueOrientated");
        greedyAlgorithm(((o1, o2) -> o2.getValue() - o1.getValue()));
    }


    @Test
    public void testWeightOrientated() {
        System.out.println("testWeightOrientated");
        greedyAlgorithm(Comparator.comparingInt(Article::getWeight));
    }

    @Test
    public void testValueDensityOrientated() {
        System.out.println("testWeightOrientated");
        greedyAlgorithm((o1, o2) -> Double.compare(o2.getValueDensity(), o1.getValueDensity()));
    }
}
