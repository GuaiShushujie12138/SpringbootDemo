package com.jarvis.springboot.algorithm.dp;

/**
 * 跳台阶
 * <p>
 * 台阶总共有 n 层, 每次只能跳一层或者两层, 问有多少种方式到最上面
 */
public class JumpStep {

    /**
     * 状态转移: dp(n) = dp(n - 1) + dp(n - 2)
     *
     * @return
     */
    public int getSolutionCount(int n) {
        if (n < 3) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
