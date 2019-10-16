package com.jarvis.springboot.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class Q0014_LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"aca", "cba"};
        log.info("最长公共前缀:" + new Q0014_LongestCommonPrefix().longestCommonPrefix(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 1) {
            return "";
        }

        int len = 0;
        outer:
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            len++;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i + 1 || strs[j].charAt(i) != c) {
                    len--;
                    break outer;
                }
            }
        }

        return strs[0].substring(0, len);
    }
}
