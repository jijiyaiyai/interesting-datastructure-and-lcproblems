package com.franky.UnionFind;

/**
 * 并查集
 * 把每个节点都编号，保存在root中
 */
public class UnionFind {
    int[] roots;
    public UnionFind(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    public int find(int i) {
        if (roots[i] == i) {
            return i;
        }
        //递归地查找，查找过程中顺便将子节点全部挂在同一个根节点上
        return roots[i] = find(roots[i]);
    }

    // 判断 p 和 q 是否在同一个集合中
    public boolean isConnected(int p, int q) {
        return find(q) == find(p);
    }

    // 合并 p 和 q 到一个集合中
    public void union(int p, int q) {
        roots[find(p)] = find(q);
    }
}

