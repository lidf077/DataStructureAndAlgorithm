package com.dongfang.dsa.algorithm.greedy;

import lombok.Data;

@Data
public class Article {
    private int weight;
    private int value;
    private double valueDensity;

    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valueDensity = value * 1.0 / weight;
    }
}
