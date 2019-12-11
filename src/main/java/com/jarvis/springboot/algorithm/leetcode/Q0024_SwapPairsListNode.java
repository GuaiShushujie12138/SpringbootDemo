package com.jarvis.springboot.algorithm.leetcode;

import lombok.ToString;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q0024_SwapPairsListNode {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);

        System.out.println("before:" + l1);
        System.out.println("result:" + new Q0024_SwapPairsListNode().swapPairs(l1));
    }

    /**
     * 从头到尾, 两两交换
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dump = new ListNode(0);
        dump.next = head;

        ListNode tmp;
        ListNode left;
        ListNode right;

        if (head != null && head.next != null) {
            left = head;
            right = head.next;

            left.next = right.next;
            right.next = left;

            dump.next = right;

            tmp = left;
            while (tmp.next != null && tmp.next.next != null) {
                left = tmp.next;
                right = tmp.next.next;

                left.next = right.next;
                right.next = left;

                tmp.next = right;
                tmp = left;
            }
        }

        return dump.next;
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
