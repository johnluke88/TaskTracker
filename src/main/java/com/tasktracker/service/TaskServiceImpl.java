package com.tasktracker.service;

import com.tasktracker.exception.NoTaskFoundException;
import com.tasktracker.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private ArrayList<Task> listOfTasks;

    public TaskServiceImpl(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    @Override
    public Task addTask(String description) {
        Task task = new Task(getMaxId(), description, "to-do", LocalDateTime.now().toString(), LocalDateTime.now().toString());
        listOfTasks.add(task);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        return listOfTasks.stream().sorted(Comparator.comparing(Task::getId)).toList();
    }

    @Override
    public void updateTask(String id, String description) {
        Task taskToUpdate = findTask(Integer.parseInt(id));

        Task taskUpdated = new Task(taskToUpdate.getId(), description, "to-do",taskToUpdate.getCreatedAt(), LocalDateTime.now().toString());

        listOfTasks.remove(taskToUpdate);
        listOfTasks.add(taskUpdated);

    }

    private Task findTask(int id) {
        return listOfTasks.stream().filter(task -> task.getId() == id).findFirst().orElseThrow(() -> new NoTaskFoundException(String.format("Task with id %d not found", id)));
    }


    private int getMaxId() {
        return listOfTasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
    }
}
