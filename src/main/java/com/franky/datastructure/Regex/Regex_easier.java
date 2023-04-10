package com.franky.datastructure.Regex;

public class Regex_easier {
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 初始化：因为星号才能匹配空字符串，所以只有当模式p的前j个字符均为星号时，dp[0][j]才为真。
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        // dp[i][0]恒为false，因为空的regex不可能匹配非空字符串

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    // *可以匹配空字符，所以有使用这个*或者不使用*
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String reg = "*";
        String str = "aa";
        System.out.println(isMatch(str,reg));
    }
}
