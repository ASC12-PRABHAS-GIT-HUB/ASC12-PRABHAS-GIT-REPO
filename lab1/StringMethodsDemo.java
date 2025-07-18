package com.demo.lab1;

import java.lang.String;

public class StringMethodsDemo {
    public void charAt(String str,int word){
        System.out.println("Charatcer At (\""+word+"\") "+str.charAt(word));
    }

    public static void Contains(String str,String search) {
        System.out.println("contains(" + search + "): " + str.contains(search));
    }

    public static void Length(String str) {
        System.out.println("length : " + str.length());
    }

    public static void IndexOf(String str, String search) {
        System.out.println("indexOf(\"" + search + "\"): " + str.indexOf(search));
    }

    public static void Equals(String str1, String str2) {
        System.out.println("\"" + str1 + "\".equals(\"" + str2 + "\"): " + str1.equals(str2));
    }

    public static void EqualsIgnoreCase(String str1, String str2) {
        System.out.println("\"" + str1 + "\".equalsIgnoreCase(\"" + str2 + "\"): " + str1.equalsIgnoreCase(str2));
    }

    public static void Join(String delimiter, String... elements) {
        System.out.println("join(): " + String.join(delimiter, elements));
    }

    public static void LastIndexOf(String str, String search) {
        System.out.println("lastIndexOf(\"" + search + "\"): " + str.lastIndexOf(search));
    }

    public static void Substring(String str, int start, int end) {
        System.out.println("substring(" + start + ", " + end + "): " + str.substring(start, end));
    }

    public static void ToLowerCase(String str) {
        System.out.println("toLowerCase(): " + str.toLowerCase());
    }

    public static void ToUpperCase(String str) {
        System.out.println("toUpperCase(): " + str.toUpperCase());
    }

    public static void Trim(String str) {
        System.out.println("trim(): \"" + str.trim() + "\"");
    }
    public static void main(String[] args) {
        String str="Ascendion is a great place to work for";
        int word= 10;
        String search="is";
        String str1="java";
        String str2="java";
        String str3="JAVA";
        StringMethodsDemo wd= new StringMethodsDemo();
        System.out.println("The given string is :"+str);

        wd.charAt(str,word);
        Contains(str,"office");
        Contains(str,"Ascendion");
        Length(str);
        IndexOf(str,search);
        Equals(str1,str2);
        EqualsIgnoreCase(str1,str3);
        Join(" ","We","are","indians");
        Join("+","Math","is","intersting");
        LastIndexOf(str,search);
        ToUpperCase(str);
        ToLowerCase(str);
        Trim(str);
    }
}
