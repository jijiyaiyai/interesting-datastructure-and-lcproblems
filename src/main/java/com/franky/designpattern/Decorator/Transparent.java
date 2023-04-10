package com.franky.designpattern.Decorator;

interface IBeauty {
    int getBeautyValue();
}

class People implements IBeauty {
    @Override
    public int getBeautyValue() {
        return 100;
    }
}

// 戒指装饰器
class RingDecorator implements IBeauty {
    private final IBeauty me;

    public RingDecorator(IBeauty me) {
        this.me = me;
    }

    @Override
    public int getBeautyValue() {
        return me.getBeautyValue() + 20;
    }
}

// 项链装饰器
class NecklaceDecorator implements IBeauty {
    private final IBeauty me;

    public NecklaceDecorator(IBeauty me) {
        this.me = me;
    }

    @Override
    public int getBeautyValue() {
        return me.getBeautyValue() * 80;
    }
}

public class Transparent {
    public static void main(String[] args) {
        IBeauty me = new People();
        System.out.println("我原本的颜值：" + me.getBeautyValue());

        // 随意挑选装饰
        IBeauty meWithNecklace = new NecklaceDecorator(me);
        System.out.println("戴上了项链后，我的颜值：" + meWithNecklace.getBeautyValue());

        // 任意搭配装饰
        IBeauty meWithNecklaceAndRing = new NecklaceDecorator(new RingDecorator(me));
        System.out.println("戴上戒指、项链后，我的颜值：" + meWithNecklaceAndRing.getBeautyValue());
        IBeauty meWithRingAndNecklace = new RingDecorator(new NecklaceDecorator(me));
        System.out.println("戴上戒指、项链后，我的颜值：" + meWithRingAndNecklace.getBeautyValue());
    }
}

