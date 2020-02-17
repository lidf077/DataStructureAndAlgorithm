package com.dongfang.dsa.structure.ch6_map;

import com.dongfang.dsa.structure.ch5_set.Set;
import com.dongfang.dsa.tools.Times;
import com.dongfang.dsa.tools.file.FileInfo;
import com.dongfang.dsa.tools.file.Files;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

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


    private static class Person {
        private int age;
        private float height;
        private String name;

        public Person(int age, float height, String name) {
            this.age = age;
            this.height = height;
            this.name = name;
        }

        /**
         * 用来比较2个对象是否相等
         */
        @Override
        public boolean equals(Object obj) {
            // 内存地址
            if (this == obj) return true;
            if (obj == null || obj.getClass() != getClass()) return false;
            // if (obj == null || !(obj instanceof Person)) return false;

            // 比较成员变量
            Person person = (Person) obj;
            return person.age == age
                    && person.height == height
                    && (Objects.equals(person.name, name));
        }

        @Override
        public int hashCode() {
            int hashCode = Integer.hashCode(age);
            hashCode = hashCode * 31 + Float.hashCode(height);
            hashCode = hashCode * 31 + (name != null ? name.hashCode() : 0);
            return hashCode;
        }
    }

    @Test
    public void testHashAndEquals() {
        java.util.Map<Person, String> map = new HashMap<>();
        Person p1 = new Person(21, 178.6f, "jack");
        map.put(p1, p1.name);
        Person p2 = new Person(21, 178.6f, "jack");
        map.put(p2, p2.name);
        Person p3 = new Person(21, 178.6f, "jack");
        map.put(p3, p3.name);
        System.out.println("map.size() = " + map.size());
    }
}
