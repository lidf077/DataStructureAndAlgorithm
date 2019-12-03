package com.dongfang.dsa.structure.ch1_list.linked;

import com.dongfang.dsa.structure.ch1_list.List;
import com.dongfang.dsa.structure.ch1_list.arrayed.ArrayList;
import org.junit.Test;
/*
https://visualgo.net/zh/
* */
public class LinkedListTest {
    @Test
    public void test001() {
        List<Integer> list = new ArrayList<>();
        list.add(20);
        list.add(0, 10);
        System.out.println("list = " + list);
        list.add(30);
        System.out.println("list = " + list);
        list.add(list.size(), 40);
        System.out.println("list = " + list);
        Integer remove = list.remove(1);
        System.out.println("remove = " + remove);
        System.out.println("list = " + list);
        System.out.println(list.get(1));
    }
}
