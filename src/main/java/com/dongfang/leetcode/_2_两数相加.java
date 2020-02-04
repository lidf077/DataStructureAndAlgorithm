package com.dongfang.leetcode;//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class _2_两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;

        int carry = 0;
        int sum;

        ListNode res = new ListNode(-1);
        ListNode p = res;
        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val + carry;
            p.next = new ListNode(sum % 10);
            carry = sum / 10;
            p = p.next;

            p1 = p1.next;
            p2 = p2.next;
        }

        if (p1 != null) {
            while (p1 != null) {
                sum = p1.val + carry;
                p.next = new ListNode(sum % 10);
                carry = sum / 10;

                p = p.next;
                p1 = p1.next;
            }
        }

        if (p2 != null) {
            while (p2 != null) {
                sum = p2.val + carry;
                p.next = new ListNode(sum % 10);
                carry = sum / 10;

                p = p.next;
                p2 = p2.next;
            }
        }

        if (carry != 0) {
            p.next = new ListNode(carry);
        }

        return res.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
