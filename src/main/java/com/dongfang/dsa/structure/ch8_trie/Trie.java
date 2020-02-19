package com.dongfang.dsa.structure.ch8_trie;

import java.util.HashMap;
import java.util.Map;

public class Trie<V> {
    private int size;
    private Node<V> root;

    public Trie() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.word;
    }

    public V add(String key, V value) {
        keyCheck(key);

        // 创建根节点
        if (root == null) root = new Node<>(null);

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            boolean emptyChildren = node.children == null;
            // 用children的时候才创建map
            Node<V> childNode = emptyChildren ? null : node.children.get(c);
            if (childNode == null) {
                childNode = new Node<>(node);
                childNode.character = c;
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c, childNode);
            }
            node = childNode;
        }

        if (node.word) { // 已经存在这个单词
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        // 新增一个单词
        node.word = true;
        node.value = value;
        size++;
        return null;
    }

    public V remove(String key) {
        // 找到最后一个节点
        Node<V> node = node(key);
        // 如果不是单词结尾，不用做任何处理
        if (node == null || !node.word) return null;

        V oldValue = node.value;

        // 如果node还有子节点
        if (node.children != null && !node.children.isEmpty()) {
            node.word = false;
            node.value = null;
            return oldValue;
        }

        // node没有子节点
        Node<V> parent = null;
        while ((parent = node.parent) != null) {
            // 不存在parent.children为空的情况
            parent.children.remove(node.character);
            if (parent.word || !parent.children.isEmpty()) break;
            node = parent;
        }
        node.parent.children.remove(node.character);
        return oldValue;
    }

    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }

    private Node<V> node(String key) {
        keyCheck(key);

        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.children == null || node.children.isEmpty()) return null;
            char c = key.charAt(i); // dog
            node = node.children.get(c);
        }

        return node;
    }

    private void keyCheck(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be null or empty");
        }
    }

    private static class Node<V> {
        Character character;
        Node<V> parent;
        Map<Character, Node<V>> children; // 每一个节点的子节点，根据字符去找子节点
        V value; // word为true，单词结尾的时候才存储value
        boolean word; // 是否为单词的结尾，是否为一个完整的单词

        Node(Node<V> parent) {
            this.parent = parent;
        }
    }
}