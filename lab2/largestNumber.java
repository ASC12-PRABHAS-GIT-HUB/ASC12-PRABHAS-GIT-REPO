package com.demo.lab2;
import java.util.Scanner;
import java.lang.Math;

public class largestNumber {
    public static int largest(int a, int b, int c) {
        return  Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) {
        int num1,num2,num3;
        int large;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number 1");
        num1=sc.nextInt();
        System.out.println("Enter number 2");
        num2=sc.nextInt();
        System.out.println("Enter number 3");
        num3=sc.nextInt();
        large=largest(num1,num2,num3);
        System.out.println("The Largest Number is : "+large);
    }
}

