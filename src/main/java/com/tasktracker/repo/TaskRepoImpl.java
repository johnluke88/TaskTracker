package com.tasktracker.repo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tasktracker.model.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskRepoImpl implements TaskRepo {

    @Override
    public void saveToJson(String name, ArrayList<Task> listOfTasks) throws IOException {
        Path path = Paths.get(name);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(path))) {
            gson.toJson(listOfTasks, writer);
        }
    }

    public ArrayList<Task> uploadFromJson(String name) throws IOException {
        Path path = Paths.get(name);
        if (Files.exists(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(path))) {
                return new ArrayList<>(Arrays.asList(gson.fromJson(reader, Task[].class)));
            }
        }
        return new ArrayList<>();
    }
}
