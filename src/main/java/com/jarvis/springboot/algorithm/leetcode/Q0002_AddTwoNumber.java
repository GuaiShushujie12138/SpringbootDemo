package com.jarvis.springboot.algorithm.leetcode;

import lombok.ToString;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Q0002_AddTwoNumber {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(6);
        l1.next = new ListNode(3);

        ListNode l2 = new ListNode(9);
        l2.next = l1;

        System.out.println("" + new Q0002_AddTwoNumber().addTwoNumbers(l1, l2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = 0;
        for (int i = 0; ; ) {
            if (l1 == null) {
                break;
            }
            num1 = (long) (num1 + l1.val * Math.pow(10, i));
            l1 = l1.next;
            i++;
        }

        long num2 = 0;
        for (int i = 0; ; ) {
            if (l2 == null) {
                break;
            }
            num2 = (long) (num2 + l2.val * Math.pow(10, i));
            l2 = l2.next;
            i++;
        }

        ListNode result = null;
        if (num1 + num2 == 0) {
            return new ListNode(0);
        }
        for (long sum = num1 + num2; sum > 0; sum /= 10) {
            int end = (int) (sum % 10);

            if (result == null) {
                result = new ListNode(end);
            } else {
                ListNode listNode = new ListNode(end);
                ListNode tmpNode = result;
                for (; tmpNode.next != null; tmpNode = tmpNode.next) {
                }
                tmpNode.next = listNode;
            }
        }

        return result;
    }

    private void putListNodeTail(ListNode result, ListNode listNode) {
        ListNode tmpNode = result;
        for (; tmpNode.next != null; tmpNode = tmpNode.next) {
        }
        tmpNode.next = listNode;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode tail = null;
        boolean overFlow = false;
        boolean lastOverFlow = false;
        for (; ; ) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = num1 + num2;
            if (sum >= 10) {
                sum -= 10;
                overFlow = true;
            } else {
                overFlow = false;
            }

            if (result == null) {
                result = new ListNode(sum);
                tail = result;
                lastOverFlow = overFlow;
            } else {
                if (lastOverFlow) {
                    sum += 1;
                    if (sum >= 10) {
                        sum -= 10;
                        lastOverFlow = true;
                    } else {
                        lastOverFlow = overFlow;
                    }
                } else {
                    lastOverFlow = overFlow;
                }

                ListNode listNode = new ListNode(sum);
                tail.next = listNode;
                tail = listNode;
            }

            boolean l1Done = l1 == null || (l1 = l1.next) == null;
            boolean l2Done = l2 == null || (l2 = l2.next) == null;
            if (l1Done && l2Done && !lastOverFlow) {
                break;
            }
        }

        return result;
    }

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode tail = null;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = num1 + num2 + carry;
            int val = sum % 10;
            carry = sum / 10;

            if (result == null) {
                result = new ListNode(val);
                tail = result;
            } else {
                ListNode listNode = new ListNode(val);
                tail.next = listNode;
                tail = listNode;
            }

            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }

        return result;
    }
}

@ToString
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
