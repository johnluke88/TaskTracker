package com.tasktracker;

import com.tasktracker.model.Task;
import com.tasktracker.repo.TaskRepoImpl;
import com.tasktracker.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceImplTest {

    private TaskServiceImpl taskService;

    @BeforeEach
    void initializationTaskServiceImpl() {
        taskService = new TaskServiceImpl(new ArrayList<>(), new TaskRepoImpl());
    }

    @Test
    void checkOneTaskCreation() {
        Task task = addOneTask();
        taskService.getAllTasks();
        assertEquals(1, task.getId());

    }

    @Test
    void checkTwoTaskCreation() {
        Task task1 = addOneTask();
        Task task2 = addOneTask();
        taskService.getAllTasks();

        assertEquals(1, task1.getId());
        assertEquals(2, task2.getId());

    }

    @Test
    void checkGetAllTasks() {
        addOneTask();
        addOneTask();
        addOneTask();
        addOneTask();

        List<Task> listOfTasks = taskService.getAllTasks();

        assertEquals(4, listOfTasks.size());
        assertEquals(1, listOfTasks.get(0).getId());
        assertEquals(4, listOfTasks.get(3).getId());
    }

    private Task addOneTask() {
        return taskService.addTask("Learning Python");
    }
}
