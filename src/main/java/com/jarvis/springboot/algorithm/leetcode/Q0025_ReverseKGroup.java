package com.jarvis.springboot.algorithm.leetcode;

import lombok.ToString;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q0025_ReverseKGroup {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        System.out.println("before:" + listNode);
//        System.out.println("reversed to 3:" + new Q0025_ReverseKGroup().reverseTo(listNode, listNode.next.next.next));
        System.out.println("reverse 2 group:" + new Q0025_ReverseKGroup().reverseKGroup(listNode, 2));

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode begin, end;

        begin = head;
        end = head;

        for (int i = 0; i < k; i++) {
            if (end == null) {
                return head;
            }
            end = end.next;
        }

        ListNode prefix = reverseTo(begin, end);
        head.next = reverseKGroup(end, k);

        return prefix;
    }

    /**
     * 翻转单个链表
     *
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr, next;
        curr = head;
        next = head;

        while (curr != null) {
            next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }

        return pre;
    }

    /**
     * 翻转到 end 节点的链表
     *
     * @param head
     * @param end
     * @return
     */
    private ListNode reverseTo(ListNode head, ListNode end) {
        ListNode pre = null;
        ListNode curr, next;
        curr = head;
        next = head;

        while (curr != end) {
            next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }

        return pre;
    }

    @ToString
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
