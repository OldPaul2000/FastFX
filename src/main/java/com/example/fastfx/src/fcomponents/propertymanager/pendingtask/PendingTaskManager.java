package com.example.fastfx.src.fcomponents.propertymanager.pendingtask;

import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;

/**
 * This class' purpose is to set some properties only after<br>
 * the element was added to a parent in order to prevent<br>
 * null pointer exception when trying to access the parent<br>
 * and its properties (e.g. width or height).<br>
 * For example if we want to set the child's layoutX<br>
 * to 50% and it hasn't any parent, a <strong>PendingTask</strong><br>
 * for applying the property will be added on the list of tasks<br>
 * and it will be executed only after the child was added in a parent.
 */
public class PendingTaskManager {
    private Region parent;
    private Map<String, PendingTask> pendingTasks;

    public PendingTaskManager () {
        this.pendingTasks = new HashMap<>();
    }

    /**
     * Sets the parent of the component which will be styled
     */
    public void setParent (Region parent) {
        this.parent = parent;
    }

    /**
     * Adds a new task
     *
     * @param id The id of the task
     * @param pendingTask the task to be added
     */
    public void addTask (String id, PendingTask pendingTask) {
        pendingTasks.put(id, pendingTask);
    }

    /**
     * Executes all the pending tasks
     */
    public void executeAllTasks () {
        pendingTasks.forEach((taskId, pendingTask) -> {
            pendingTask.execute(parent);
        });
    }

    /**
     * Removes a specific task by specifying its id
     *
     * @param id The task id
     */
    public void removeTaskById (String id) {
        pendingTasks.remove(id);
    }

    /**
     * Removes all the existing tasks
     */
    public void removeAllTasks () {
        pendingTasks.clear();
    }
}
