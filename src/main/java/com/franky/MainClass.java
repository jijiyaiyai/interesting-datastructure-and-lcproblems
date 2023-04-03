package com.franky;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        long res = 0L;
        int startIndex = 0;
        boolean nega = false;
        if(!Character.isDigit(s.charAt(0))){
            if(s.charAt(0) == '-')nega = true;
            else if(s.charAt(0) != '+')return -1;
            startIndex++;
        }
        for(int i = startIndex; i<s.length(); i++){
            char c = s.charAt(i);
            if(!Character.isDigit(c))break;
            res*=10;
            res+=Integer.parseInt(String.valueOf(c));
            if(res>Integer.MAX_VALUE || res<Integer.MIN_VALUE)
                return nega?Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        if(nega){
            res = -res;
        }
        return (int) res;
    }
}

public class MainClass {
    public static String stringToString(String input) {
        return JsonArray.readFrom("[" + input + "]").get(0).asString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            int ret = new Solution().myAtoi(s);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}