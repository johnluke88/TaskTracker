package com.tasktracker;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        checkArgs(args);

        if (args[0].equalsIgnoreCase("add") && args[1] != null) {
            addTask(args[1]);
        } /*else if (args[0].equalsIgnoreCase("update") && args[1] != null) {
            try {
                Integer id = Integer.valueOf(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("You must type a mumber after update command");
                System.exit(1);
            }

        } else if (args[0].equalsIgnoreCase("mark-in-progress") && args[1] != null) {
            try {
                Integer id = Integer.valueOf(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("You must type a mumber after mark-in-progress command");
                System.exit(1);
            }
        } else if (args[0].equalsIgnoreCase("mark-done") && args[1] != null) {
            try {
                Integer id = Integer.valueOf(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("You must type a mumber after mark-done command");
                System.exit(1);
            }
        } else if (args[0].equalsIgnoreCase("list")) {
        } else if (args[0].equalsIgnoreCase("list") && (args[1] != null && args[1].equalsIgnoreCase("done"))) {
        } else if (args[0].equalsIgnoreCase("list") && (args[1] != null && args[1].equalsIgnoreCase("todo"))) {
        } else if (args[0].equalsIgnoreCase("list") && (args[1] != null && args[1].equalsIgnoreCase("in-progress"))) {
        }*/ else {
            System.err.println("You must type a valid command");
            System.exit(1);
        }
    }

    private static void checkArgs(String[] args) {
        if (args.length == 0) {
            System.err.println("You must type a command input");
            System.exit(1);
        } else if (args.length == 1 && !args[0].equalsIgnoreCase("list")) {
            System.err.println("You must type a valid argument");
            System.exit(1);
        }
    }

    private static boolean checkIfFileExists(Path myPath) {
        return Files.exists(myPath) || Files.isDirectory(myPath);
    }

    private static void addTask(String description) throws IOException {
        Path myPath = Paths.get("tasks.json");
        boolean check = checkIfFileExists(myPath);

        // create a Gson instance with pretty-printing
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Task> tasks = new LinkedList<>();
        if (!check) {
            Files.createFile(myPath);
            Task t = new Task(1, description, "to-do", LocalDateTime.now().toString(), LocalDateTime.now().toString());
            tasks.add(t);
            writeObjectToJsonFile(myPath, gson, tasks);
            if(t != null) {
                System.out.println("Task with id "  + t.getId() + " inserted!");
            } else {
                System.exit(1);
            }
        } else {
            tasks = readObjectsFromJsonFile(myPath, gson);
            if (!tasks.isEmpty()) {
                Task taskMaxId = tasks.stream().max(Comparator.comparing(Task::getId)).get();
                Task t = new Task(taskMaxId.getId() + 1, description, "to-do", LocalDateTime.now().toString(), LocalDateTime.now().toString());
                tasks.add(t);

                writeObjectToJsonFile(myPath, gson, tasks);
                if(t != null) {
                    System.out.println("Task with id "  + t.getId() + " inserted!");
                } else {
                    System.exit(1);
                }
            } else {
                System.exit(1);
            }
        }
    }

    private static List<Task> readObjectsFromJsonFile(Path myPath, Gson gson) {
        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(myPath))) {
            return new LinkedList<>(Arrays.asList(gson.fromJson(reader, Task[].class)));
        } catch (IOException e) {
            System.err.println("It's not possible to read file: " + e.getMessage());
        }
        return new LinkedList<>();
    }

    private static void writeObjectToJsonFile(Path myPath, Gson gson, List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(myPath))) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("It's not possible to write on file: " + e.getMessage());
        }
    }

}