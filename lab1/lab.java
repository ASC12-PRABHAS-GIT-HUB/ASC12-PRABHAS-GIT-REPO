package com.demo.lab1;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class lab{
    public int add(int a,int b){
        return a+b;

    }
    public int add1(int a,int b, int c){
        return a+b+c;

    }
    public double add2(double c, double d){
        return c+d;
    }

    public String add4(String s,int n){
        return s+" "+n;
    }

    public static void main(String[] args) {
        lab lb= new lab();

        System.out.println("add(10,20)=" +lb.add(10,20));
        System.out.println("add(10,20,30)=" +lb.add1(10,20,30));
        System.out.println("add(10.4,29.5)=" +lb.add2(10.4,29.5));
        System.out.println("Hello + 20="+ lb.add4("HEllo",20));
    }
}

