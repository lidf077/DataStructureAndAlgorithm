package com.dongfang.dsa.algorithm.graph;

/**
 * 图 Graph
 * 	1、基本概念
 * 			1、图由顶点（vertex）和边（dege）组成，通常表示成为 G = (V, E)
 * 			2、G 表示一个图，V是顶点集，E是边集
 * 			3、顶点集V有穷且非空
 * 			4、任意两个顶点之间都可以用边来表示它们之间的联系，边集E可以是空的
 * 	2、应用举例
 * 			1、图结构的应用极其广泛
 * 				社交网络、地图导航、游戏开发
 * 	3、有向图（Directed Graph）
 * 			1、有向图的边是有明确方向的
 * 			2、有向无环图（Directed Acyclid Graph 简称DAG)
 * 				如果一个有向图，从任意顶点出发无法经过若干条边回到该顶点，那么它就是一个有向无环图
 * 			3、出度、入度
 * 				1、出度、入度适用于有向图
 * 				2、出度（Out-degree）一个顶点的出度为x，是指有x条边以该顶点为起点
 * 				3、入度（In-degree） 一个顶点的入度为x，是指有x条边以该顶点为终点
 * 	4、无向图（Undirected Graph）
 * 		1、无向图的边是无方向的
 * 	5、混合图（Mixed Graph）
 * 		1、混合图的边可能是无向的，也可能是有向的
 * 	6、简单图、多重图
 * 		1、平行边：
 * 			1、在无向图中，关联一对顶点的无向边如果多于一条，则称这些边为平等边
 * 			2、在有向图中，关联一对顶点的有向边如果多于一条，并且它们的方向相同，则称这些边为平行边
 * 		2、多重图（Multigraph）
 * 			1、有平等图或者自环的图
 * 		3、简单图（Simple Graph）
 * 			1、既没有平等边也没有自环的图
 * 			2、课程中讨论的都是简单图
 * 	7、无向完全图（Undirected Complete Graph）
 * 		1、无向完全图的任意两个顶点之间都存在边
 * 		2、n个顶点的无向完全图有n(n-1)/2条边 C_n^2
 * 	8、有向完全图（Directed Complete Graph）
 * 		1、有向完全图的任意两个顶点之间存在方向相反的两条边
 * 		2、n个顶点的有向完全图有n(n-1)条边
 * 		3、稠密图（Dense Graph）:边数接近或者等于完全图
 * 		4、稀疏图（Sparse Graph）：边的数量远远少于完全图
 * 	9、有权图（Weighted Graph）
 * 		1、有权图的边可以拥有权值（Weight)
 * 	10、连通图（Connected Graph）
 * 		1、如果顶点x和y之间存在可相互抵达的路径（间接或者直接的路径），则称x和y是连通的
 * 		2、如果无向图G的任意2个顶点之间是连通的，则称G为连通图
 * 	11、连通分量（Connected Component）
 * 		1、连通分量：无向图的极大连通子图
 * 		2、连通图只有一个连通分量，即其自身，非连通的无向图有多个连通分量
 * 	12、强连通图（Strongly Connected Graph）
 * 		1、如果有向图G中的任意2个顶点都是连通的，则称G为强连通图
 * 	13、强连通分量（Strongly Connected Component）
 * 		1、强连通分量：有向图的极大强连通子图
 * 		2、强连通图只有一个强连通分量，即其自身，非强连通的有向图有多个强连通分量
 *
 *
 * 图的实现方式
 * 	图有2种常见的实现方案
 * 	邻接矩阵（Adjacency Matrix）
 * 	邻接表（Adjacency List）
 * 		1、邻接矩阵（Adjacency Matrix）
 * 			1、一维数组存放顶点信息
 * 			2、二维数组存放边信息
 * 			3、邻接矩阵比较适合稠密图，不然传统比较浪费内存 n^2
 * 			4、如果是有权值，二维数组中存放权值
 * 		2、邻接表（Adjacency List）
 *
 * @param <V>
 * @param <W>
 */
public interface Graph<V, W> {
    // 边数
    int edgeSize();

    // 顶点数
    int verticesSize();

    // 只添加一个顶点，没有边
    void addVertex(V v);

    void removeVertex(V v);

    // 添加一条边
    void addEdge(V from, V to, W weight);

    void addEdge(V from, V to);

    void removeEdge(V from, V to);

}
