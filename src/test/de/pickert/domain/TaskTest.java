package de.pickert.domain;

import de.pickert.service.TaskController;

import static org.junit.Assert.assertEquals;

import org.junit.*;


class TaskTest {
    Task task = new Task("testTitle","testDescription");

    @Test
    void setTitleShouldSetNewTitle() {
        task.setTitle("title Changed");
        assertEquals("title Changed",task.getTitle());
    }

    @Test
    void setStatusShouldSetStatusNEW() {
        task.setStatus(Status.NEW);
        assertEquals("NEW",task.getStatus().toString());
    }
    @Test
    void setStatusShouldSetStatusPROGRESS() {
        task.setStatus(Status.PROGRESS);
        assertEquals("PROGRESS",task.getStatus().toString());
    }

    @Test
    void setResponsibleShouldSetResponsible() {
        task.setResponsible("NewResponsible");
        assertEquals("NewResponsible", task.getResponsible());
    }


}