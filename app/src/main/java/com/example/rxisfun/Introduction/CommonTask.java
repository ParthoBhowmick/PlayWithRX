package com.example.rxisfun.Introduction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author partho
 * @since 13/9/21
 */
public class CommonTask {

    private static List<Task> tasks = new ArrayList<>();

    public static List<Task> createTasksList(){
        tasks.add(new Task("Take out the trash", true, 3));
        tasks.add(new Task("Walk the dog", false, 2));
        tasks.add(new Task("Make my bed", true, 1));
        tasks.add(new Task("Unload the dishwasher", false, 0));
        tasks.add(new Task("Make dinner", true, 5));
        return tasks;
    }

    public static List<Task> getTaskList() {
        return tasks;
    }

}
