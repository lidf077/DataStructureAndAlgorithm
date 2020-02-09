package com.dongfang.dsa.structure.ch3_queue;

import com.dongfang.dsa.structure.ch1_list.List;
import com.dongfang.dsa.structure.ch1_list.linked.LinkedList;

/**
 * 队列是一种特殊的线性表，只能在头尾两端进行操作
 *
 * 能在头部删除，在尾部添加的数据结构
 *
 * 队尾：只能从队尾添加元素，一般叫做enQueue 入队
 *
 * 队头：只能从队头移除元素，一般叫做deQueue 出队
 *
 * DeQueue
 *      双端队列是能在头尾两端添加、删除的队列
 *      英文deque 是double end queue的简称
 * @param <E>
 */
public class Queue<E> {
    private List<E> queue;

    public Queue() {
        // 优先使用双向链表，因为队列主要是往队头插入元素
        queue = new LinkedList<>();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(E element) {
        queue.add(element);
    }

    public E deQueue() {
        return queue.remove(0);
    }

    public E front() {
        return queue.get(0);
    }

    public void clear() {
        queue.clear();
    }
}
