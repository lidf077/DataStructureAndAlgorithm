package com.dongfang.dsa.structure.ch1_list.arrayed;

import org.junit.Test;

public class ArrayListTest {
    @Test
    public void testIndexOutOfBound() {
        IntArrayList arrayList = new IntArrayList(100);
        // java.lang.IndexOutOfBoundsException: index: -4, size: 0
        int res = arrayList.get(-4);
    }

    @Test
    public void testArrayListAdd() {
        IntArrayList list = new IntArrayList();
        list.add(33);
        list.add(99);
        System.out.println("list = " + list);
    }

    @Test
    public void testRegex() {
        String str = "\"latitude\": \"-32.336533\",\n" +
                "\"latitude\": \"32.336533\",\n" +
                "\"foo\": \"14\"\n" +
                "\"bla\": \"12.\"\n" +
                "\"to7\": \"12.a\"";
        //String replace = str.replaceAll("\\\"(-{0,1}[0-9]+(\\.[0-9]*){0,1})\\\"", "$1");

        String replace = str.replaceAll("\"(-?[0-9]+(\\.[0-9]*)?)\"", "$1");


        //String replaceAll = str.replaceAll("\"(\\d+)\"", "$1");

        System.out.println("str = " + str);
        System.out.println("--------------");
        System.out.println("replace = " + replace);

    }



    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"name\":\"")
                    .append(name).append('\"');
            sb.append(",\"age\":")
                    .append(age);
            sb.append('}');
            return sb.toString();
        }

        // 自定义对象的比较方法
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        // 销毁时调用
        @Override
        protected void finalize() throws Throwable {
            super.finalize();

            System.out.println("Person -finialize");
        }
    }

    @Test
    public void testFinalize() {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("tom", 23));
        persons.add(new Person("jack", 22));
        assert persons.size() == 2;
        persons.clear();

        // 提醒jvm进行垃圾回收
        System.gc();


    }
}
