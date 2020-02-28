package com.dongfang.dsa.algorithm.graph;

import org.junit.Test;

public class GraphDemo {
    @Test
    public void testGraph() {
        Graph<String, Integer> graph = new ListGraph<>();
        // add edge时，顶点不存在，则添加，存在，则直接复用
        graph.addVertex("V0");
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);


    }
}
