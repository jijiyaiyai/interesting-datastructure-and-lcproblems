package com.franky.designpattern.Factory;

interface IFactory {
    Fruit create();
}
class AppleFactory_ implements IFactory {
    @Override
    public Fruit create(){
        return new Apple();
    }
}
class PearFactory_ implements IFactory {
    @Override
    public Fruit create(){
        return new Pear();
    }
}

// 抽象工厂模式，适合横向扩展而不是纵向拓展

public class AbstractFactory {
    public static void main(String[] args) {
        IFactory appleFactory = new AppleFactory_();
        Fruit apple = appleFactory.create();
        IFactory pearFactory = new PearFactory_();
        Fruit pear = pearFactory.create();
        apple.eat();
        pear.eat();
    }
}
