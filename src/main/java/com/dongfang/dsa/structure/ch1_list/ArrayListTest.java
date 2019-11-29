package com.dongfang.dsa.structure.ch1_list;

import org.junit.jupiter.api.Test;

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
}
