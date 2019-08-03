package com.jarvis.springboot.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 */
public class Q0006_ZStringTransform {

    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        int rowNums = 4;
        System.out.println("result:" + new Q0006_ZStringTransform().convert(s, rowNums));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> list = Lists.newArrayList();
        // 形成 Z 的字符行数
        int rows = Math.min(numRows, s.length());
        for (int i = 0; i < rows; i++) {
            list.add(new StringBuilder());
        }

        // 从第一行开始往下, 到最后一行后往上, 保存字符到相应的 stringBuilder 中
        int currRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            list.get(currRow).append(c);

            if (currRow == 0 || currRow == rows - 1) {
                goingDown = !goingDown;
            }

            currRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : list) {
            result.append(sb);
        }
        return result.toString();
    }
}
