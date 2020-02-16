package com.dongfang.dsa.structure.ch5_set;

import com.dongfang.dsa.structure.ch1_list.List;
import com.dongfang.dsa.structure.ch1_list.linked.LinkedList;

public class ListSet<E> implements Set<E> {
    private List<E> listSet;

    public ListSet() {
        // 使用自己实现的链表来做底层实现
        listSet = new LinkedList<>();
    }

    @Override
    public int size() {
        return listSet.size();
    }

    @Override
    public boolean isEmpty() {
        return listSet.isEmpty();
    }

    @Override
    public void clear() {
        listSet.clear();
    }

    @Override
    public boolean contains(E element) {
        return listSet.contains(element);
    }

    // 集合是否支持空，由自己定义
    @Override
    public void add(E element) {
/*        if (listSet.contains(element)) return;
        listSet.add(element);*/
        int index = listSet.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUNT) {
            listSet.set(index, element); // 进行覆盖操作
        } else {
            listSet.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int index = listSet.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUNT) {
            listSet.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;
        for (int i = 0; i < listSet.size(); i++) {
            if (visitor.visit(listSet.get(i))) return;
        }
    }
}
