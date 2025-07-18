package com.demo.lab1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;

public class ArraySort {
    static Scanner sc=new Scanner(System.in);
    private static void print(int size,int[] num){
        System.out.println("Printing using FOR LOOP");
        for(int i=0;i<size;i++){
            System.out.print(num[i]+" ");
        }
        int i=0;
        System.out.println("\nPrinting using While LOOP");
        while(i<size){
            System.out.print(num[i]+" ");
            i++;
        }
    }
    private static void sort(int[] num){
        System.out.println("\nBefore sorting :"+Arrays.toString(num));
        Arrays.sort(num);
        System.out.println("After sorting : "+Arrays.toString(num));
    }
    private static void search(int[] num,int size){
        System.out.println("Enter a number u want to search");
        int value=sc.nextInt();
        int counter=0;
        for(int i=0;i<size;i++) {
            if (num[i] == value) {
                counter++;
            }
        }
        if(counter>0)
            System.out.println("Number Found and is repeated for "+counter+" times");
        else
            System.out.println("Number not found");

    }
    private static void add(int[] num){
        System.out.println("Enter the position where u want to add an element");
        int position=sc.nextInt();
        System.out.println("Enter the element u want to add");
        int newValue=sc.nextInt();

        int[] newNum=new int[num.length+1];
        for(int i=0;i<position;i++){
            newNum[i]=num[i];
        }
        newNum[position]=newValue;
        for(int i=position;i<num.length;i++){
            newNum[i+1]=num[i];
        }
        System.out.println("Original Array :"+Arrays.toString(num));
        System.out.println("Updated Array :"+Arrays.toString(newNum));

    }
    private static void RemoveDuplicate(int[] arr){
        HashSet<Integer> set= new HashSet<>();
        for(int num:arr)
            set.add(num);
//        System.out.println("Original Array : "+Arrays.toString(arr));
        System.out.println("Updated Array After removing Duplicates: "+set);

    }
    public static void main(String[] args) {
        int size;
        System.out.println("Enter the size of an array");
        size=sc.nextInt();
        int[] num=new int[size];
        System.out.println("Enter "+size+" Numbers");
        for(int i=0;i<size;i++){
            num[i]=sc.nextInt();
        }
        print(size,num);
        sort(num);
        search(num,size);
        add(num);
        RemoveDuplicate(num);

    }
}