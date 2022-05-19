package de.pickert.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {
    TaskController taskController = new TaskController();

    @Test
    void isForceResponsibleReturnsTrue() {
      taskController.setForceResponsible(true);
      assertTrue(taskController.isForceResponsible());
    }

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
    @Test
    void isForceCommentTrueWhenSetForceComment(){
        taskController.setForceComment(true);
        assertTrue(taskController.isForceComment());
    }
    @Test
    void isForceCommentFalseWhenNotSetForceComment(){
        assertFalse(taskController.isForceComment());
    }
    @Test
    void isForceResponsibleTrueWhenSetForceResponsible(){
        taskController.setForceResponsible(true);
        assertTrue(taskController.isForceResponsible());
    }
    @Test
    void isForceResponsibleFalseWhenNotSetForceResponsible(){
        assertFalse(taskController.isForceResponsible());
    }
}