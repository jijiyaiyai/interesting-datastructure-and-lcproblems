package com.franky.designpattern.Singleton;

// 饿汉模式

//public class Singleton {
//
//    private static Singleton instance = new Singleton();
//
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        return instance;
//    }
//}

// 懒汉模式
public class Singleton {

    // 双重判空模式下，JVM有时会对指令重排序，导致其他线程获得不为空但是未被初始化的对象实例
    // 加上volatile禁止重排序
    private static volatile Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) { // 有可能当你获得锁的时候其他线程已经初始化好了
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
