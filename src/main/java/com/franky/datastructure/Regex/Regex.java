package com.franky.datastructure.Regex;

public class Regex {

    public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        // dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];

        // 初期值
        // s为空，p为空，能匹配上
        dp[0][0] = true;
        // p为空，s不为空，必为false(boolean数组默认值为false，无需处理)

        // s为空，p不为空，由于*可以匹配0个字符，所以有可能为true
        for (int j = 1; j <= cp.length; j++) {
            if (cp[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        // i动就是文本串动了，j动就是模式串动了
        for (int i = 1; i <= cs.length; i++) {
            for (int j = 1; j <= cp.length; j++) {
                // 文本串和模式串末位字符能匹配上
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {
                    //各自退一个字符
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cp[j - 1] == '*') { // 模式串末位是*
                    // 模式串*的前一个字符能够跟文本串的末位匹配上
                    // 或者模式串的前一个字符是.，可以匹配任意字符
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
                        // *匹配0次的情况，意思是使用*消掉前面那个字符
                        // *匹配1次或多次的情况，*发挥作用重复很多遍，所以j不动
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];

                    } else { // 模式串*的前一个字符不能够跟文本串的末位匹配
                        dp[i][j] = dp[i][j - 2];     // *只能匹配0次
                    }
                }
                //剩下的情况就是不匹配了
            }
        }
        return dp[cs.length][cp.length];
    }

}
