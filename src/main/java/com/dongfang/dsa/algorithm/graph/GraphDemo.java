package com.dongfang.dsa.algorithm.graph;

import org.junit.Test;

public class GraphDemo {
    @Test
    public void testGraph() {
        Graph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V1", "V0", 9);

    }
}
