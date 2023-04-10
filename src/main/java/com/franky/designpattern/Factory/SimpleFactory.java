package com.franky.designpattern.Factory;

interface Fruit {
    public void eat();
}
class Apple implements Fruit {
    public Apple(){}

    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}
class Pear implements Fruit {
    public Pear(){}

    @Override
    public void eat() {
        System.out.println("吃梨子");
    }
}
// 简单工厂
class FruitFactory {
    public Fruit create(String type) {
        return switch (type) {
            case "苹果" ->new Apple();
            //还可以隐藏苹果类的生产过程
                //AppleSeed appleSeed = new AppleSeed();
                //Sunlight sunlight = new Sunlight();
                //Water water = new Water();
                //new Apple(appleSeed, sunlight, water);
            case "梨子" -> new Pear();
            default -> throw new IllegalArgumentException("暂时没有这种水果");
        };
    }
}

// 工厂方法模式
class AppleFactory{
    public Fruit create(){
        return new Apple();
    }
}
class PearFactory{
    public Fruit create(){
        return new Pear();
    }
}
public class SimpleFactory {
    public static void main(String[] args) {
        FruitFactory fruitFactory = new FruitFactory();
        Fruit apple = fruitFactory.create("苹果");
        Fruit pear = fruitFactory.create("梨子");
        apple.eat();
        pear.eat();

        //工厂方法虽然导致需要知道每一个工厂，但是还是封装了生产的过程，而且避免了工厂变得臃肿
        AppleFactory af = new AppleFactory();
        PearFactory pf = new PearFactory();
        Fruit apple_ = af.create();
        Fruit pear_ = pf.create();
        apple_.eat();
        pear_.eat();
    }
}
