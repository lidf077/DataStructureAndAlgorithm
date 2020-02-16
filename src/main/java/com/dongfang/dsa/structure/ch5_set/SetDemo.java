package com.dongfang.dsa.structure.ch5_set;

import com.dongfang.dsa.structure.Visitor;
import com.dongfang.dsa.tools.Times;
import com.dongfang.dsa.tools.file.FileInfo;
import com.dongfang.dsa.tools.file.Files;
import org.junit.Test;


public class SetDemo {
    @Test
    public void testListSet() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(1);
        listSet.add(2);
        listSet.add(1);
        listSet.add(3);
        listSet.add(2);
        listSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println("element = " + element);
                return false;
            }
        });
    }

    @Test
    public void testTreeSet() {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println("element = " + element);
                return false;
            }
        });
    }
    @Test
    public void testTreeSetPerformance() {
        Set<Integer> treeSet = new TreeSet<>();
        int num = 1_000_000;
        for (int i = 0; i < num; i++) {
            treeSet.add(i);
        }

        for (int i = 0; i < num; i++) {
            treeSet.remove(i);
        }

    }

    @Test
    public void testTreeSetWords() {
        FileInfo fileInfo = Files.read("D:\\ubuntu\\learn\\JavaWeb\\MavenProject\\maven03\\src\\java.base",
                new String[]{"java"});

        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);

//		Times.test("ListSet", new Task() {
//			public void execute() {
//				testSet(new ListSet<>(), words);
//			}
//		});

        Times.test("TreeSet", new Times.Task() {
            public void execute() {
                testSet(new TreeSet<>(), words);
            }
        });
    }

    static void testSet(Set<String> set, String[] words) {
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.contains(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.remove(words[i]);
        }
    }
}
