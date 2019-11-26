package com.jarvis.springboot.algorithm.leetcode;

import lombok.ToString;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q0019_RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);

        System.out.println("result:" + new Q0019_RemoveNthFromEnd().removeNthFromEnd2(listNode, 1));
    }

    /**
     * 解法一 : (两遍遍历) 先遍历一遍整个链表, 得出链表的长度, 就可以计算出需要删除的链表节点正数的位置了, 再遍历一遍链表就可以
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        int size = 1;
        ListNode curr = head;
        while (curr.next != null) {
            size++;
            curr = curr.next;
        }

        if (size < 2) {
            return null;
        }

        if (size == n) {
            return head.next;
        }

        int removeIndex = size - n;
        ListNode result = head;

        int index = 0;
        while (result != null && result.next != null) {
            index++;

            if (index == removeIndex) {
                if (n == 1) {
                    result.next = null;
                } else {
                    result.next = result.next.next;
                }
            }

            result = result.next;
        }

        return head;
    }

    /**
     * 解法二 : (一遍遍历) 新建一个 dump 节点, next 指向 head 节点, 两个指针同时指向 dump 节点, 第一个指针先单独往后移动 n 次,
     * 然后和第二个指针一起往后移动整个 size - n 的距离, 到第一个指针的 next 为 null, 这个时候第二个指针刚好就是在要被删除的节点之前
     * 一个, 控制第二个指针的 next 指针跳过下一个节点就可以完成删除节点操作
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dump = new ListNode(0);
        dump.next = head;

        ListNode first = dump;
        ListNode second = dump;

        while (n > 0) {
            n--;
            first = first.next;
        }

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return dump.next;
    }

    @ToString
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}


