package com.demo.lab2.pc4;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the Meeting Room Application!");
        Booking bookings[] = new Booking[10]; // Array to hold bookings
        bookings[0] = new Booking("E123", "MR001", "2025-07-15", "01:00 AM", "2 hours");
        bookings[1] = new Booking("E124", "MR002", "2025-07-16", "02:00 PM", "1 hour");
        System.out.println("Bookings created successfully!");

        for(Booking booking : bookings) {
            if (booking != null) {
                // System.out.println("Booking for Employee ID: " + booking.getEmployeeId() +
                //                    ", Meeting Room ID: " + booking.getMeetingRoomId() +
                //                    ", Date: " + booking.getMeetingDate() +
                //                    ", Time: " + booking.getMeetingTime() +
                //                    ", Duration: " + booking.getDuration());
                System.out.println(booking);
            }
        }
    }
}