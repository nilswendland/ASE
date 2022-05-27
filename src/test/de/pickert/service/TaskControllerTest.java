package de.pickert.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {
    TaskController taskController = new TaskController();


    @Test
    void editedTrueWhenStringNotEmpty() {
        assertTrue(taskController.edited("something"));
    }
    @Test
    void editedFalseWhenStringEmpty() {
        assertFalse(taskController.edited(""));
    }

    @Test
    void isValidDateReturnTrueWhenDateFormatIsValid() {
        assertTrue(taskController.isValidDate("2023-12-23"));
    }
    @Test
    void isValidDateReturnFalseWhenDateFormatIsInvalid() {
        assertFalse(taskController.isValidDate("2023/12/12"));
    }


}