package com.franky.designpattern.Decorator;

interface IHouse {
    void live();
}
class House implements IHouse{
    @Override
    public void live() {
        System.out.println("房屋原有的功能：居住功能");
    }
}
interface IStickyHookHouse extends IHouse{
    void hangThings();
}
class StickyHookDecorator implements IStickyHookHouse {
    private final IHouse house;

    public StickyHookDecorator(IHouse house) {
        this.house = house;
    }

    @Override
    public void live() {
        house.live();
    }

    @Override
    public void hangThings() {
        System.out.println("有了粘钩后，新增了挂东西功能");
    }
}


public class semi_Transparent {
    public static void main(String[] args) {
        IStickyHookHouse stickyHookHouse = new StickyHookDecorator(new House());
        stickyHookHouse.live();
        stickyHookHouse.hangThings();
    }
}
