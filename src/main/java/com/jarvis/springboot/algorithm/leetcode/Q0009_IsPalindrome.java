package com.jarvis.springboot.algorithm.leetcode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q0009_IsPalindrome {

    public static void main(String[] args) {
        int x = 12321;
        System.out.println("是否为回文数字:" + new Q0009_IsPalindrome().isPalindrome3(x));
    }

    /**
     * 解法 1 : 转换成字符串进行反转比较是否为回文数字
     *
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        String reverse = new StringBuilder().append(x).reverse().toString();
        return (x + "").equals(reverse);
    }

    /**
     * 解法 2, 首尾比较, 再比较之后的数字的首尾
     *
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        // 负数一定不是回文数
        if (x < 0) return false;

        int div = 1;
        // 找出能获得 x 的最高位数字的除数, 比如 x = 5333, 那么 div 应该等于 1000
        while (x / div >= 10) div *= 10;
        while (x > 0) {
            if (x / div != x % 10) return false;

            // x 等于去除已经比较的首尾两数字之后的数
            x = (x % div) / 10;

            // 因为去除了两位数, 所以 div = div / 100
            div /= 100;
        }

        return true;
    }

    /**
     * 解法 3, 比较后一半数字的反转是不是等于前一半数字
     *
     * @param x
     * @return
     */
    public boolean isPalindrome3(int x) {
        // 负数以及能非 0 能整除 10 的数一定不是回文数字
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversedNum = 0;
        while (x > reversedNum) {
            reversedNum = reversedNum * 10 + x % 10;
            x /= 10;
        }

        // 奇数长度的数字 reversedNum 会比前一半数多一个数字, 除以 10 之后就完全一致
        return x == reversedNum || x == reversedNum / 10;
    }
}
