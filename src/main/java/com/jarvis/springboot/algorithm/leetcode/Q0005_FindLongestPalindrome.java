package com.jarvis.springboot.algorithm.leetcode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Q0005_FindLongestPalindrome {

    public static void main(String[] args) {
        String s = "bb";
        System.out.println("longest palindrome:" + new Q0005_FindLongestPalindrome().longestPalindrome(s));
    }

    /**
     * 中心扩展算法, 选定一个中心从两边一直扩, 一共有 2 * length - 1 个中心点
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String maxChild = "";
        int size = s.length();

        for (int i = 0; i < 2 * size - 1; i++) {
            int index = i / 2;
            int left = i % 2 == 0 ? index - 1 : index;
            int right = index + 1;
            while (left >= 0 && right < size && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            maxChild = maxChild.length() > right - left - 1 ? maxChild : s.substring(left + 1, right);
        }

        return maxChild;
    }
}
