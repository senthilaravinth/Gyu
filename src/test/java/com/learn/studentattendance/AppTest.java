package com.learn.studentattendance;

// These are JUnit 5 (Jupiter) imports
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testInvalidStudentName() {
        App myApp = new App();
        
        // JUnit 5 Rule: (Condition, "Message")
        boolean result = myApp.recordAttendance("", "Present");
        assertFalse(result, "Should return false for empty name");
    }

    @Test
    public void testNullStudentName() {
        App myApp = new App();
        
        // JUnit 5 Rule: (Condition, "Message")
        boolean result = myApp.recordAttendance(null, "Present");
        assertFalse(result, "Should return false for null name");
    }
}