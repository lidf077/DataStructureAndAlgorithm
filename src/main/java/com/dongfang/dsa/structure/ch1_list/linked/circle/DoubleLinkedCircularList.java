package com.dongfang.dsa.structure.ch1_list.linked.circle;

import com.dongfang.dsa.structure.ch1_list.AbstractList;

public class DoubleLinkedCircularList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private Node<E> current; //用于指向某个节点

    private static class Node<E> {
        // 只用在里面，不用private
        E element;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    // 官方的LinkedList将每个节点内容置为null，因此其中可能有迭代器引用
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
        // gc root被局部变量所指向的对象，没有被gc root所引用，就直接释放内存了
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

        if (index == size) {
            Node<E> oldLast = last;
            last = new Node<>(last, element, first);
            if (oldLast == null) { // 链表添加第一个元素
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;
            prev.next = node;
            if (index == 0) { // next == first 这样判断也可以
                first = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;

            prev.next = next;
            next.prev = prev;

            if (node == first) { // index == 0 node == first
                first = next;
            }

            if (node == last) { // index = size - 1
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    @Override
    public void remove(E element) {
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
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
        Node<E> node = first;
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

    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    public E remove() {
        if (current == null) return null;
        Node<E> next = current.next;
        E element = current.element;
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }
        return element;
    }
}
