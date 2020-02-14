package com.dongfang.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 面试题_01_01_判定字符是否唯一 {
    public boolean isUnique(String astr) {
        if (astr.length() > 128) {
            return false;
        }
        boolean[] charSet = new boolean[128];
        for (int i = 0; i < astr.length(); i++) {
            int val = astr.charAt(i);
            if (charSet[val]) { // already found this char in string
                return false;
            }

            charSet[val] = true;
        }

        return true;
    }

    public boolean isUnique3(String astr) {
        int bit = 0;
        for (int i = 0; i < astr.length(); i++) {
            if (((bit >> (astr.charAt(i) - 'a')) & 1) == 1) {
                return false;
            }
            bit |= 1 << (astr.charAt(i) - 'a');
        }
        return true;
    }

    public boolean isUnique2(String astr) {
        if (astr.isEmpty()) return true;
        char[] chars = astr.toCharArray();
        Arrays.sort(chars);
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) return false;
        }
        return true;
    }


    public boolean isUnique1(String astr) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < astr.length(); i++) {
            if (set.contains(astr.charAt(i))) return false;
            set.add(astr.charAt(i));
        }
        return true;
    }
}
