package com.dongfang.leetcode.swordtooffer;//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
//


//leetcode submit region begin(Prohibit modification and deletion)
public class _面试题05_替换空格 {
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            if (aChar == ' ') count++;
        }
        if (count == 0) return s;

        char[] newChars = new char[chars.length + 2 * count];
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                newChars[j] = '%';
                newChars[j + 1] = '2';
                newChars[j + 2] = '0';
                j += 3;
            } else {
                newChars[j++] = chars[i];
            }
        }

        return new String(newChars);
    }

    public String replaceSpace1(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            if (aChar == ' ') count++;
        }
        if (count == 0) return s;

        char[] newChars = new char[chars.length + 2 * count];
        int j = newChars.length - 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                newChars[j] = '0';
                newChars[j - 1] = '2';
                newChars[j - 2] = '%';
                j -= 3;
            } else {
                newChars[j--] = chars[i];
            }
        }

        return new String(newChars);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
