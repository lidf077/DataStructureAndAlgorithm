package com.dongfang.dsa.structure.ch1_list.linked;

import com.dongfang.dsa.structure.ch1_list.AbstractList;

import java.util.IdentityHashMap;

/**
 * 虚拟头节点，有时候为了让代码更加简洁，统一所有结点的处理逻辑，可以在最前面增加虚拟头节点，不存储数据
 *
 * @param <E>
 */
public class LinkedListWithDummyHead<E> extends AbstractList<E> {
    private Node<E> first;

    public LinkedListWithDummyHead() {
        first = new Node<>(null, null);
    }

    private static class Node<E> {
        // 只用在里面，不用private
        E element;
        Node<E> next;

        // 创建一个节点，存放此节点的值与指向的下一个节点
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;

        return old;
    }

    // 注意边界测试 0 size - 1
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node<E> prev = (index == 0) ? first : node(index - 1);
        prev.next = new Node<>(element, prev.next);

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> prev = index == 0 ? first : node(index - 1);
        E old = prev.next.element;
        prev.next = prev.next.next;

        size--;
        return old;
    }

    @Override
    public void remove(E element) {

    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first.next;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUNT;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("size=").append(size).append(", [");
        Node<E> node = first.next;
        for (int i = 0; i < size; i++) {
            res.append(node.element);
            if (i != (size - 1)) res.append(", ");
            node = node.next;
        }

        Node<E> cur = first;
        while (cur != null) {
            cur = cur.next;
        }

        res.append("]");
        return res.toString();
    }


    /**
     * 获取index位置对应的节点对象
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first.next;
        // 循环走index次
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}
