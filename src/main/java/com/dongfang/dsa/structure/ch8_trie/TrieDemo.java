package com.dongfang.dsa.structure.ch8_trie;

import org.junit.Test;

public class TrieDemo {
    @Test
    public void testTrieAdd() {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("东方", 5);
        trie.add("doggy", 6);
        System.out.println("trie.size() = " + trie.size());
        System.out.println("trie.get(\"东方\") = " + trie.get("东方"));
        System.out.println("trie.contains(\"dog\") = " + trie.contains("dog"));

        System.out.println("trie.startsWith(\"ca\") = " + trie.startsWith("ca"));
        System.out.println("trie.startsWith(\"东\") = " + trie.startsWith("东"));
        System.out.println("trie.startsWith(\"方\") = " + trie.startsWith("方"));
        System.out.println("trie.remove(\"catalog\") = " + trie.remove("catalog"));
        System.out.println("trie.remove(\"东方\") = " + trie.remove("东方"));
        System.out.println("trie.remove(\"doggy\") = " + trie.remove("doggy"));
    }
}