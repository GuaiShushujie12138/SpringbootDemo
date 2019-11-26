package com.jarvis.springboot.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q0020_BracketsIsValid {

    private static Map<Character, Character> brackets;

    public static void main(String[] args) {
        String s = "{{[])}";
        System.out.println("valid:" + new Q0020_BracketsIsValid().isValid(s));
    }

    static {
        brackets = new HashMap<>();

        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put('{', '}');
    }

    /**
     * 解法 : 借用 stack 数据结构特性, 后入先出, 左括号往 stack 中 push, 碰到右括号就 pop 一个元素比对,
     * 如果刚好是栈顶元素的匹配右括号, 那么就继续向下执行, 最后 stack 为 null 代表匹配完成, 返回 true
     * 如果栈顶元素不匹配, 那么直接返回 false
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        char[] chs = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (brackets.containsKey(chs[i])) {
                stack.push(chs[i]);
            } else if (brackets.containsValue(chs[i])) {
                if (!stack.empty() && Objects.equals(brackets.get(stack.peek()), chs[i])) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }
}
