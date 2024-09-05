package com.tasktracker.service;

import com.tasktracker.exception.NoTaskFoundException;
import com.tasktracker.model.Task;

import java.util.List;
import java.util.Scanner;

public class InputManagerImpl implements InputManager{

    private Scanner in;
    private TaskService taskService;

    public InputManagerImpl(Scanner in, TaskService taskService) {
        this.in = in;
        this.taskService = taskService;
    }


    @Override
    public int add() {
        System.out.println("Type description for Task.");
        String description = in.nextLine().trim().toLowerCase();
        Task task = taskService.addTask(description);
        return task.getId();
    }

    @Override
    public List<Task> list() {
        List<Task> listOfTasks = taskService.getAllTasks();
        return listOfTasks;
    }

    @Override
    public void update() {
        System.out.println("Type Task id to update.");
        String id = in.nextLine().trim().toLowerCase();

        System.out.println("Type new description for Task.");
        String description = in.nextLine().trim().toLowerCase();

        taskService.updateTask(id, description);

    }
}
