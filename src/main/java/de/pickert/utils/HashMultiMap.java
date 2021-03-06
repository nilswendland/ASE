package de.pickert.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.pickert.domain.Task;

public class HashMultiMap extends HashMap<String, List<Task>> {

    public void put(String key, Task task) {
        List<Task> current = get(key);
        if (current == null) {
            current = new ArrayList<Task>();
            super.put(key, current);
        }
        current.add(task);
    }
}
