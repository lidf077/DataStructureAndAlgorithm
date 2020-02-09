package com.dongfang.dsa.structure.ch3_queue;

import com.dongfang.dsa.structure.ch1_list.List;
import com.dongfang.dsa.structure.ch1_list.linked.LinkedList;

/**
 * 队列是一种特殊的线性表，只能在头尾两端进行操作
 *
 * DeQueue
 *      双端队列是能在头尾两端添加、删除的队列
 *      英文deque 是double end queue的简称
 */
public class Dequeue<E> {
    private List<E> dequeue;

    public Dequeue() {
        // 优先使用双向链表，因为队列主要是往队头插入元素
        dequeue = new LinkedList<>();
    }

    public int size() {
        return dequeue.size();
    }

    public boolean isEmpty() {
        return dequeue.isEmpty();
    }

    public void enqueueRear(E element) {
        dequeue.add(element);
    }

    public E deQueueRear() {
        return dequeue.remove(dequeue.size() - 1);
    }

    public void enqueueFront(E element) {
        dequeue.add(0, element);
    }

    public E deQueueFront() {
        return dequeue.remove(0);
    }

    public E front() {
        return dequeue.get(0);
    }

    public E rear() {
        return dequeue.get(dequeue.size() - 1);
    }

    public void clear() {
        dequeue.clear();
    }
}
