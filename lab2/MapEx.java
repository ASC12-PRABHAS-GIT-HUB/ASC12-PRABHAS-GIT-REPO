package com.demo.lab2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapEx {
    public static void main(String[] args){
        m1();
    }
    private static void m1(){
        Map<Object,Object> map1 = new HashMap<Object,Object>();
        Laptop laptop1 = new Laptop("Inspirion",10);
        Laptop laptop2= new Laptop("Thinkpad",10);
        map1.put("Hp",laptop1);
        String laptopBrand = "lenovo";
        map1.put(laptopBrand,laptop2);
        map1.put("HP",new Laptop("XP5",10));
        map1.put("null",new Laptop("Turf",20));
        map1.put("null",new Laptop("ROG",10));
//        map1.put(null,new Laptop("macbook",10));
//        map1.put(null,new Laptop("Vaio",10));
        map1.put("Sony",new Laptop("Vaio",10));
        System.out.println(map1);


        System.out.println("**************************");
//        Collection<Object> values = map1.values();
//        for(Object value: values){
//            System.out.println(value);
//        }

    }

}

class Laptop{
    String laptopName;
    float price;

    public Laptop(String laptopNameParam, float priceParam){
        laptopName = laptopNameParam;
        price = priceParam;
    }

    @Override
    public String toString(){
        return "Laptop [laptop name: "+laptopName+", laptop price is Rs. "+price+"]";
    }
}
