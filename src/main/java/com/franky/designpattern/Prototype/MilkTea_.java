package com.franky.designpattern.Prototype;

import java.util.HashMap;
import java.util.Map;

public class MilkTea_ implements Cloneable{
    public String type;
    public boolean ice;
    public Map<Integer,Integer> map = new HashMap<>();

    @Override
    protected MilkTea_ clone() throws CloneNotSupportedException {
        MilkTea_ res = (MilkTea_) super.clone();
        //JVM只会为你复制基本类型，非基本类型还是要自己设定拷贝方式
        //如果要深拷贝就得new
        res.map = new HashMap<>(res.map);
        return res;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        MilkTea_ milkTeaOfJay = new MilkTea_();
        milkTeaOfJay.type = "原味";
        milkTeaOfJay.ice = false;
        milkTeaOfJay.map.put(1,1);

        MilkTea_ yourMilkTea = milkTeaOfJay.clone();
        yourMilkTea.map.put(2,2);
        System.out.println(milkTeaOfJay.map);
        System.out.println(yourMilkTea.map);
    }
}
