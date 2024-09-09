package com.tasktracker;


import com.tasktracker.exception.NoTaskFoundException;
import com.tasktracker.model.Task;
import com.tasktracker.repo.TaskRepo;
import com.tasktracker.repo.TaskRepoImpl;
import com.tasktracker.service.InputManager;
import com.tasktracker.service.InputManagerImpl;
import com.tasktracker.service.TaskService;
import com.tasktracker.service.TaskServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        TaskRepo taskRepo = new TaskRepoImpl();
        TaskService taskService = new TaskServiceImpl(new ArrayList<>(), taskRepo);
        InputManager inputManager = new InputManagerImpl(in, taskService);

        String cmd;
        System.out.println("Type a command:");
        while(!(cmd = in.nextLine().toLowerCase()).equalsIgnoreCase("exit")) {
            switch (cmd) {
                case "add": add(inputManager); break;
                case "update": update(inputManager); break;
                case "list": list(inputManager); break;
                case "upload": upload(inputManager); break;
                default: System.out.println("Type a valid command!");
            }
        }

        exitAndSave(inputManager);
    }

    private static void add(InputManager inputManager) {
        int id = inputManager.add();
        System.out.println("Task with id " + id + " added");
    }

    private static void update(InputManager inputManager) {
        try {
            inputManager.update();
            System.out.println("Task updated");
        } catch (NoTaskFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void list(InputManager inputManager) {
        List<Task> listOfTasks = inputManager.list();
        for (Task task: listOfTasks) {
            System.out.println(task);
        }
    }

    private static void upload(InputManager inputManager) {
        try {
            inputManager.upload();
            System.out.println("File uploaded");
        } catch (IOException e) {
            System.out.println("Impossible to load file");
            throw new RuntimeException(e);
        }
    }

    private static void exitAndSave(InputManager inputManager) {
        try {
            inputManager.save();
            System.out.println("File saved");
        } catch (IOException e) {
            System.out.println("Impossible to save file");
            throw new RuntimeException(e);
        }
    }
}