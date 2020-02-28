package com.dongfang.dsa.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings("unchecked")
public class ListGraph<V, W> implements Graph<V, W> {
    // 用map来存储顶点，key为顶点的值，value为顶点
    // 通过顶点的value找到顶点
    private Map<V, Vertex<V, W>> vertices = new HashMap<>();
    private Set<Edge<V, W>> edges = new HashSet<>();

    public void print() {
        vertices.forEach((v, vertex) -> {
            System.out.println(v);
            System.out.println("vertex.outEdges = " + vertex.outEdges);
            System.out.println("vertex.inEdges = " + vertex.inEdges);
        });

        edges.forEach(System.out::println);
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        // 如果图中已经包含此顶点，直接返回
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public void addEdge(V from, V to, W weight) {
        // 判断顶点是否存在
        // form值对应的顶点
        Vertex<V, W> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            // 顶点如果不存在，则创建新节点，并加入顶点map中
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, W> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        // 到这里，from to上的顶点都构造好了
        // 现在看fromVertex --> toVertex之间是否有边，有的话，更新权值，没有，则添加

        // 方案1、formVertex的outEdges边中，是否有一条是从fromVertex-->toVertex
        Edge<V, W> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
/*        if (fromVertex.outEdges.contains(edge)) {
            // 边已经存在了，则更新权值
            // 但是hashSet中是无法更新的
        }*/

        // 直接把边删除了，再加进去，删除了edge equals的边
        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        edges.remove(edge);

        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void addEdge(V from, V to) {
        // 没有权值，写成空
        addEdge(from, to, null);
    }

    @Override
    public void removeEdge(V from, V to) {

    }

    private static class Vertex<V, W> {
        V value;
        Set<Edge<V, W>> inEdges = new HashSet<>();
        Set<Edge<V, W>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        // 两个顶点的value相同，则是同一个顶点
        @Override
        public boolean equals(Object obj) {
            return Objects.equals(value, ((Vertex<V, W>) obj).value);
        }

        @Override
        public int hashCode() {
            // equals通过value比较
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
//            return "Vertex [value = " + valStr + ", inEdges = " + inEdges + ", outEdges = " + outEdges + "]";
        }
    }

    private static class Edge<V, W> {
        Vertex<V, W> from;
        Vertex<V, W> to;
        W weight;

        public Edge(Vertex<V, W> from, Vertex<V, W> to) {
            this.from = from;
            this.to = to;
        }

        // 相等逻辑，起点和终点相同，就是同一条边，权值是不断变化的，因此不做比较
        @Override
        public boolean equals(Object obj) {
            Edge<V, W> edge = (Edge<V, W>) obj;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }


        // equals返回true，同一个对象，hashCode一定要相等
        @Override
        public int hashCode() {
            int fromHashCode = from.hashCode();
            int toHashCode = to.hashCode();
            return fromHashCode * 31 + toHashCode;
        }

        @Override
        public String toString() {
            return "Edge [from = " + from + ", to = " + to + ", weight = " + weight + "]";
        }
    }
}
