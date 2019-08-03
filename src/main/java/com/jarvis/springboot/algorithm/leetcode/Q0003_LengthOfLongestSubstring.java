package com.jarvis.springboot.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class Q0003_LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "abba";
        System.out.println("最长子串长度:" + new Q0003_LengthOfLongestSubstring().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int result = 0;

        for(int start = 0, end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            if (map.containsKey(c)) {
                // 不能单纯的使用 map.get(c), 因为 start 可能比他大了
                start = Math.max(start, map.get(c));
            }

            // 为了上面的 Math.max(start, map.get(c))
            result = Math.max(result, end - start + 1);

            map.put(c, end + 1);
        }

        return result;
    }
}
