
package com.demo.lab1;
import java.util.Scanner;
public class MultiplicationTable {
    public void multiplyFor(int product){
        for(int i=1;i<=10;i++){
            System.out.println(product+"X"+i+"="+product*i);
        }
    }
    public void multiplyWhile(int product){
//        System.out.println("USing While Loop");
        int i=1;
        while(i<=10){
            System.out.println(product+"X"+i+"="+product*i);
            i++;
        }
    }
    public void multiplyDoWhile(int product){
        int i=1;
        do {
            System.out.println(product+"X"+i+"="+product*i);
            i++;
        }while(i<=10);
    }
    public static void main(String[] args) {
        int product;
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter a Number");
        product=sc.nextInt();
        MultiplicationTable mul=new MultiplicationTable();
        System.out.println("Multiplication Table Using FOR LOOP");
        mul.multiplyFor(product);
        System.out.println("Multiplication Table Using WHILE LOOP");
        mul.multiplyWhile(product);
        System.out.println("Multiplication Table Using DO WHILE LOOP");
        mul.multiplyDoWhile(product);

    }
}
