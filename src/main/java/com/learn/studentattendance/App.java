package com.learn.studentattendance;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        App myApp = new App();
        myApp.recordAttendance("Senthil", "Present");

    }
    /**
     * Records a student's attendance status.
     * Returns true if the name is valid, false otherwise.
     */
    public boolean recordAttendance(String studentName, String status) {
        if (studentName == null || studentName.trim().isEmpty()) {
            System.out.println("Error: Student name cannot be empty.");
            return false;
        }

        System.out.println("Attendance recorded for: " + studentName + " [" + status + "]");
        return true;
    }
}
