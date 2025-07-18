package com.demo.lab2.pc4;

public class Booking {
    // The employeeID is a property of the booking.
    // Properties will have getters and setters.
    private String employeeId;
    private String meetingRoomId;
    // Explore the datate time api.
    private String meetingDate;
    private String meetingTime;
    private String duration;

    // Overloaded constructor
    public Booking(String employeeId, String meetingRoomId, String meetingDate, String meetingTime, String duration) {
        this.employeeId = employeeId;
        this.meetingRoomId = meetingRoomId;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.duration = duration;
    }

    // @Override
    // The toString method is overridden to provide a string representation of the Booking object.
    public String toString() {
        return "Booking for Employee ID: " + employeeId +
                ", Meeting Room ID: " + meetingRoomId +
                ", Date: " + meetingDate +
                ", Time: " + meetingTime +
                ", Duration: " + duration;
    }
    // String getEmployeeId() {
    //     return employeeId;
    // }
    // String getMeetingRoomId() {
    //     return meetingRoomId;
    // }
    // String getMeetingDate() {
    //     return meetingDate;
    // }
    // String getMeetingTime() {
    //     return meetingTime;
    // }
    // String getDuration() {
    //     return duration;
    // }

}