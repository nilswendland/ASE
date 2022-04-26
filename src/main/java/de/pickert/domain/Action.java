package de.pickert.domain;

import de.pickert.service.TaskController;
import de.pickert.utils.UserScanner;

public abstract class Action {
    public abstract void execute();

    public static TaskController controller;
    public static UserScanner scanner;

    protected Action() {
        controller = TaskController.getInstance();
        scanner = UserScanner.getInstance();
    }
}