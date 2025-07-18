package com.demo.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class student {
    int studentId,marks1,marks2,marks3;
    String studentName,city;
    double feeperMonth,studentFee,AvgMarks,TotalMarks;
    boolean isscholarshipavaliable;
    Scanner sc= new Scanner(System.in);
    BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));


    private void studentDetails() throws IOException {

        System.out.println("Enter student ID");
        studentId=sc.nextInt();
        System.out.println("Enter the name of the student");
        studentName= bf.readLine();
        System.out.println("Enter the city");
        city= bf.readLine();
        System.out.println("Enter the marks in subject 1");
        marks1=sc.nextInt();
        System.out.println("Enter the marks in subject 2");
        marks2=sc.nextInt();
        System.out.println("Enter the marks in subject 3");
        marks3=sc.nextInt();

    }
    public void getAnnualFee(){
        System.out.println("Enter the Fee of the Student");
        feeperMonth=sc.nextInt();
        studentFee=feeperMonth*12;
        System.out.println("The Annual Fee of student is "+ studentFee);
    }
    public void getTotalMarks(){
        TotalMarks=marks1+marks2+marks3;
        System.out.println("The Total marks of "+studentName+" is : "+ TotalMarks );
    }
    public void getAverageMarks(){
        AvgMarks=TotalMarks/3;
        System.out.println("The Average marks of "+studentName+" is : "+ AvgMarks );
    }
    public void getResult(){
        if(marks1 >= 60 && marks2>=60 && marks3 >=60)
            System.out.println("Passed in all subjects" );
        else
            System.out.println("Failed!!");

    }

    public void DataOut(){
        System.out.println("ID of student : "+studentId);
        System.out.println("Name of student : "+studentName);
        System.out.println("Student's City: "+city);
        System.out.println("Marks in subject 1 : "+marks1);
        System.out.println("Marks in subject 2 : "+marks2);
        System.out.println("Marks in subject 3 : "+marks3);
//        System.out.println("Name of student : "+marks3);

    }

    public static  void main(String[] args) throws IOException {
        student s1=new student();
        student s2=new student();
        int n=3;
        for(int i=0;i<=n;i++){
            System.out.println(s2  );;
        }

          s1.getAnnualFee();
          s1.DataOut();
          s1.getTotalMarks();

          s1.getAverageMarks();
          s1.getResult();
          s2.studentDetails();

    }


}




