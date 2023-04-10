package com.franky.datastructure.Trie;

/**
 * 字典树的实现
 * 如果仅作查询使用的话，不需要保存具体的字符串值
 */
public class Trie {
    class Node{
        public Node[] next;
        public boolean isWord = false;
        public Node(){
            next = new Node[26];
        }
    }
    Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for(int i = 0; i<word.length(); i++){
            char c = word.charAt(i);
            if(cur.next[c-'a'] == null){
                cur.next[c-'a'] = new Node();
            }
            cur = cur.next[c-'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        Node cur = root;
        for(int i = 0; i<word.length(); i++){
            char c = word.charAt(i);
            if(cur.next[c-'a'] == null){
                return false;
            }
            cur = cur.next[c-'a'];
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for(int i = 0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.next[c-'a'] == null){
                return false;
            }
            cur = cur.next[c-'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple");
        System.out.println(t.search("apple"));
        System.out.println(t.startsWith("app"));
        System.out.println(t.search("app"));
        t.insert("app");
        System.out.println(t.search("app"));
    }
}
