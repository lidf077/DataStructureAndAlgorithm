package com.dongfang.dsa.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

@SuppressWarnings("unchecked")
public class ListGraph<V, W> implements Graph<V, W> {
    // 用map来存储顶点，key为顶点的值，value为顶点
    // 通过顶点的value找到顶点
    private Map<V, Vertex<V, W>> vertices = new HashMap<>();
    private Set<Edge<V, W>> edges = new HashSet<>();

    public void print() {
        System.out.println("打印顶点");
        vertices.forEach((v, vertex) -> {
            System.out.println(v);
            System.out.println("vertex.outEdges = " + vertex.outEdges);
            System.out.println("vertex.inEdges = " + vertex.inEdges);
        });

        System.out.println("打印边");
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
        Vertex<V, W> vertex = vertices.remove(v);
        if (vertex == null) return; // 不存在这个顶点
        // 删除v相关的边，顶点存在

/*        vertex.outEdges.forEach((edge) -> {
            removeEdge(edge.from.value, edge.to.value);
        });

        vertex.inEdges.forEach((edge) -> {
            removeEdge(edge.from.value, edge.to.value);
        });*/
        // 使用迭代器删除
        Iterator<Edge<V, W>> outEdgesIterator = vertex.outEdges.iterator();
        while (outEdgesIterator.hasNext()) {
            Edge<V, W> edge = outEdgesIterator.next();
            // edge从vertex 出发，到另一条边。也就是edge.to，edge也被 保存在edge.to.inEdges中
            edge.to.inEdges.remove(edge);
            edges.remove(edge);
            outEdgesIterator.remove();
        }
        Iterator<Edge<V, W>> inEdgesIterator = vertex.inEdges.iterator();
        while (inEdgesIterator.hasNext()) {
            Edge<V, W> edge = inEdgesIterator.next();
            // edge 到达vertex，从edge.from出发，因此也要删除edge.from.outEdges中保存的引用
            edge.from.outEdges.remove(edge);
            edges.remove(edge);
            inEdgesIterator.remove();
        }

        // 没有必要再清理vertex 的outEdges inEdges，因为vertex已经被 删除，这个方法执行完后，栈空间上的vertex也会被删除
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
/*        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        edges.remove(edge);*/
        if (fromVertex.outEdges.remove(edge)) {// 如果fromVertex的out中有，并且删除成功，那么toVertex的inEdges里也有，edges里也有
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

        // 删除完后再添加
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
        // 检查顶点是否存在
        Vertex<V, W> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, W> toVertex = vertices.get(to);
        if (toVertex == null) return;

        // 检查from --> to的边是否存在
        Edge<V, W> edge = new Edge<>(fromVertex, toVertex);

        if (fromVertex.outEdges.remove(edge)) {// 如果fromVertex的out中有，并且删除成功，那么toVertex的inEdges里也有，edges里也有
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }


    @Override
    public void bsf(V begin) {
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        // 用来统计哪些顶点已经进入过了队列中
        Set<Vertex<V, W>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, W>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, W> vertex = queue.poll();

            System.out.println("vertex.value = " + vertex.value);
            for (Edge<V, W> edge : vertex.outEdges) {
                // 将vertex出发的终止入队
                if (!visitedVertices.contains(edge.to)) {
                    queue.offer(edge.to);
                    visitedVertices.add(edge.to);
                }
            }
        }
    }

    @Override
    public void dfs(V begin) {
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, W>> visitedVertices = new HashSet<>();
        dfs(beginVertex, visitedVertices);
    }

    private void dfs(Vertex<V, W> vertex, Set<Vertex<V, W>> visitedVertices) {
        System.out.println("vertex.value = " + vertex.value);
        // 访问完，加入已访问
        visitedVertices.add(vertex);
        for (Edge<V, W> outEdge : vertex.outEdges) {
            if (!visitedVertices.contains(outEdge.to)) {
                dfs(outEdge.to, visitedVertices);
            }
        }
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