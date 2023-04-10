package com.franky.datastructure.Monotonic_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class Monotonic_stack {
    int[] nums;
    public Monotonic_stack(int[] nums){
        this.nums = nums;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }

    public int[] left_first_bigger(int[] nums, int[] res){
        int n = nums.length;
        res = new int[n];
        Deque<Integer>ddz = new ArrayDeque<>();
        for(int i = 0; i<n; i++){
            while(!ddz.isEmpty() && nums[i]>=nums[ddz.peek()]){
                ddz.pop();
            }
            //这里可以根据实际业务需求改变成0，表示自身
            res[i] = ddz.isEmpty()?-1:ddz.peek();
            ddz.push(i);
        }
        return res;
    }
    public int[] left_first_smaller(int[] nums, int[] res){
        int n = nums.length;
        res = new int[n];
        Deque<Integer>ddz = new ArrayDeque<>();
        for(int i = 0; i<n; i++){
            while(!ddz.isEmpty() && nums[i]<=nums[ddz.peek()]){
                ddz.pop();
            }
            //这里可以根据实际业务需求改变成0，表示自身
            res[i] = ddz.isEmpty()?-1:ddz.peek();
            ddz.push(i);
        }
        return res;
    }
    public int[] right_first_smaller(int[] nums, int[] res){
        int n = nums.length;
        res = new int[n];
        Deque<Integer>ddz = new ArrayDeque<>();
        //求右边的就倒着遍历
        for(int i = n-1; i>=0; i--){
            while(!ddz.isEmpty() && nums[i]<=nums[ddz.peek()]){
                ddz.pop();
            }
            //这里可以根据实际业务需求改变成i，表示自身
            res[i] = ddz.isEmpty()?n:ddz.peek();
            ddz.push(i);
        }
        return res;
    }
    public int[] right_first_bigger(int[] nums, int[] res){
        int n = nums.length;
        res = new int[n];
        Deque<Integer>ddz = new ArrayDeque<>();
        //求右边的就倒着遍历
        for(int i = n-1; i>=0; i--){
            while(!ddz.isEmpty() && nums[i]>=nums[ddz.peek()]){
                ddz.pop();
            }
            //这里可以根据实际业务需求改变成0，表示自身
            res[i] = ddz.isEmpty()?n:ddz.peek();
            ddz.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] seq = new int[10];
        // 生成随机测试序列
        for (int i = 0; i < 10; i++) {
            seq[i] = new Random().nextInt(10);
        }
        System.out.println(Arrays.toString(seq));
        Monotonic_stack m = new Monotonic_stack(seq);
        int[] res = new int[0];
        System.out.println(Arrays.toString(m.left_first_bigger(seq, res)));
        System.out.println(Arrays.toString(m.left_first_smaller(seq, res)));
        System.out.println(Arrays.toString(m.right_first_bigger(seq, res)));
        System.out.println(Arrays.toString(m.right_first_smaller(seq, res)));
    }
}
