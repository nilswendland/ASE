package de.pickert.domain;

import de.pickert.service.TaskController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Task task = new Task("testTitle","testDescription");

    @Test
    void setTitleShouldSetNewTitle() {
        task.setTitle("title Changed");
        assertEquals("title Changed",task.getTitle());
    }

    @Test
    void setStatusShouldSetNewStatus() {
        task.setStatus(Status.NEW);
        assertEquals("NEW",task.getStatus().toString());
    }

    @Test
    void setResponsibleShouldSetResponsible() {
        task.setResponsible("NewResponsible");
        assertEquals("NewResponsible", task.getResponsible());
    }

}