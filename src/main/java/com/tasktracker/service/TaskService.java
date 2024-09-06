package com.tasktracker.service;

import com.tasktracker.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface TaskService {

    Task addTask(String description);

    List<Task> getAllTasks();

    void updateTask(String id, String description);

    void saveTask(String name) throws IOException;
}
