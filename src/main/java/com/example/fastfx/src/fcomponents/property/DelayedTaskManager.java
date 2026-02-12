package com.example.fastfx.src.fcomponents.property;

import javafx.scene.layout.Region;

import java.util.HashSet;
import java.util.Set;

/**
 * This class' purpose is to set some properties only after<br>
 * the element was added to a parent in order to prevent<br>
 * null pointer exception when trying to access the parent<br>
 * and its properties (e.g. width or height).<br>
 * For example if we want to set the child's layoutX<br>
 * to 50% and it hasn't any parent, a <strong>DelayedTask</strong><br>
 * for applying the property will be added on the list of tasks<br>
 * and it will be executed only after the child was added in a parent.
 */
public class DelayedTaskManager {

    private Region parent;
    private Set<DelayedTask> delayedTasks;

    public DelayedTaskManager () {
        this.delayedTasks = new HashSet<>();
    }

    public void setParent(Region parent){
        this.parent = parent;
    }

    public void addTask(DelayedTask delayedTask){
        delayedTasks.add(delayedTask);
    }

    public void executeAllTasks(){
        delayedTasks.forEach(task -> {
            task.apply(parent);
        });
    }

    public void removeAllTasks(){
        delayedTasks.clear();
    }
}
