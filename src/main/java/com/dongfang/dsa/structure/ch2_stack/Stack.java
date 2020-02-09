package com.dongfang.dsa.structure.ch2_stack;

import java.util.LinkedList;
import java.util.List;

// java官方的stack是线程安全的
public class Stack<E> {
    private List<E> stack;

    public Stack() {
        stack = new LinkedList<>();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(E element) {
        stack.add(element);
    }

    public E pop() {
        return stack.remove(stack.size() - 1);
    }

    public E peek() {
        return stack.get(stack.size() - 1);
    }

}
