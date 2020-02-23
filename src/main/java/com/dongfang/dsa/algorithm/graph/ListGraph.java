package com.dongfang.dsa.algorithm.graph;

import java.util.HashSet;
import java.util.Set;

public class ListGraph implements Graph {

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int verticesSize() {
        return 0;
    }

    @Override
    public void addVertex(Object o) {

    }

    @Override
    public void removeVertex(Object o) {

    }

    @Override
    public void addEdge(Object from, Object to, Object weight) {

    }

    @Override
    public void addEdge(Object from, Object to) {

    }

    @Override
    public void removeEdge(Object from, Object to) {

    }




    private static class Vertex<V, W> {
        V value;
        Set<Edge<V, W>> inEdges = new HashSet<>();
        Set<Edge<V, W>> outEdges = new HashSet<>();

    }

    private static class Edge<V, W> {
        Vertex<V, W> from;
        Vertex<V, W> to;
        W weight;
    }
}
