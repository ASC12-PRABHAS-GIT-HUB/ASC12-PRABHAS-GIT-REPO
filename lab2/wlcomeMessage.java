package com.demo.lab2;
import java.util.Scanner;
public class wlcomeMessage {
    public static void welcomeMessage(String name) {
        System.out.println("Hello " + name + ", Welcome to Java World!");
    }

    public static void main(String[] args) {
        String str;
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your Name ");
        str=sc.nextLine();
        welcomeMessage(str);
        System.out.println("New Manual Feed");

    }
}
