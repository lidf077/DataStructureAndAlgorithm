package com.dongfang.dsa.algorithm.graph;

import java.util.HashSet;
import java.util.Set;

public class ListGraph<V, W> implements Graph<V, W> {


    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int verticesSize() {
        return 0;
    }

    @Override
    public void addVertex(V v) {

    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public void addEdge(V from, V to, W weight) {

    }

    @Override
    public void addEdge(V from, V to) {

    }

    @Override
    public void removeEdge(V from, V to) {

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
