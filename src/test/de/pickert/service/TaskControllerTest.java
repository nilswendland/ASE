package de.pickert.service;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;



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
        assertFalse(taskController.isValidDate("12-22-2000"));
        assertFalse(taskController.isValidDate("22-12-2000"));
        assertFalse(taskController.isValidDate("2022-22-12"));
        assertFalse(taskController.isValidDate("14.12.2022"));
    }

    @Test
    void isForceResponsibleReturnsTrueWhenForceResponsibleTrue() {
        taskController.setForceResponsible(true);
        assertTrue(taskController.isForceResponsible());
    }

}