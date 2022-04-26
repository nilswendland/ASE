package de.pickert.utils;
import java.util.List;

import de.pickert.domain.Status;
import de.pickert.domain.Task;

import java.util.HashMap;
import java.util.ArrayList;

public class StatusMultiMap extends HashMap<Status, List<Task>> {
    public void put(Status key, Task task) {
        List<Task> current = get(key);
        if (current == null) {
            current = new ArrayList<Task>();
            super.put(key, current);
        }
        current.add(task);
    }
}
