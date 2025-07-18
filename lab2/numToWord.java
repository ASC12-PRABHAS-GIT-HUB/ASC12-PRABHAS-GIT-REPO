package com.demo.lab2;

import java.util.Scanner;

public class numToWord {
    public static void printDigitsInWords(int number) {
        String[] words = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String numStr = String.valueOf(number);
        System.out.print("The converted word :");
        for (char digit : numStr.toCharArray()) {
            System.out.print(words[digit - '0'] + " ");
        }
    }

    public static void main(String[] args) {
        int num1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number ");
        num1=sc.nextInt();
        System.out.println("The Number given :"+num1);
        printDigitsInWords(num1);

    }
}
