package com.dongfang.leetcode;//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串


//leetcode submit region begin(Prohibit modification and deletion)


import jdk.nashorn.api.tree.IfTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 1、遇见左字符，将左字符入栈
 * 2、遇见右字符，
 * 如果栈是空的，说明括号无效
 * 如果栈不为空，将栈项字符弹出，与右字符匹配
 * 如果左右字符不匹配，说明括号无效
 * 如果左右字符匹配，继续扫描下一个字符
 * 3、所有字符扫描完后，
 * 栈为空，说明括号有效
 * 栈不为空，说明括号无效
 */
public class _20_有效的括号 {

    private static Map<Character, Character> map = new HashMap<>();

    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char aChar = s.charAt(i);
            if (map.containsKey(aChar)) { // 如果是左字符，就入栈
                stack.push(aChar);
            } else { // 在这个分支，只有左括号了
                if (stack.isEmpty()) return false;
                Character leftChar = stack.pop();
                if (map.get(leftChar) != aChar) return false;
            }
        }
        return stack.isEmpty();
    }


    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char aChar = s.charAt(i);
            if (aChar == '(' || aChar == '[' || aChar == '{') { // 如果是左字符，就入栈
                stack.push(aChar);
            } else { // 在这个分支，只有左括号了
                if (stack.isEmpty()) return false;
                Character leftChar = stack.pop();
                if (leftChar == '(' && aChar != ')') return false;
                if (leftChar == '[' && aChar != ']') return false;
                if (leftChar == '{' && aChar != '}') return false;
            }
        }
        return stack.isEmpty();
    }


    private boolean simple(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
