package com.tasktracker.service;

import com.tasktracker.exception.NoTaskFoundException;
import com.tasktracker.model.Task;
import com.tasktracker.repo.TaskRepo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private ArrayList<Task> listOfTasks;
    private TaskRepo taskRepo;

    public TaskServiceImpl(ArrayList<Task> listOfTasks, TaskRepo taskRepo) {
        this.listOfTasks = listOfTasks;
        this.taskRepo = taskRepo;
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

    @Override
    public void uploadTask(String name) throws IOException {
        ArrayList<Task> newListOfTasks = taskRepo.uploadFromJson(name+".json");
        listOfTasks.clear();
        listOfTasks = newListOfTasks;
    }

    @Override
    public void saveTask(String name) throws IOException {
        taskRepo.saveToJson(name+".json", listOfTasks);
    }

}
