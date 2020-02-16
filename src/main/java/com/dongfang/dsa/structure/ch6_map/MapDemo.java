package com.dongfang.dsa.structure.ch6_map;

import com.dongfang.dsa.structure.ch5_set.Set;
import com.dongfang.dsa.tools.Times;
import com.dongfang.dsa.tools.file.FileInfo;
import com.dongfang.dsa.tools.file.Files;
import org.junit.Test;

public class MapDemo {
    @Test
    public void testTreeMap() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("class", 2);
    }

    @Test
    public void testTreeMapPerformance() {
        FileInfo fileInfo = Files.read("D:\\ubuntu\\learn\\JavaWeb\\MavenProject\\maven03\\src",
                new String[]{"java"});

        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);

        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer count = map.get(words[i]);
            count = (count == null) ? 1 : (count + 1);
            map.put(words[i], count);
        }


        map.traversal(new Map.Visitor<String, Integer>() {
            public boolean visit(String key, Integer value) {
                int count = 0;
                System.out.println(key + "_" + value);
                count++;
                return count == 50;
            }
        });
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
