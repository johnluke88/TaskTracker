package com.tasktracker.service;

import com.tasktracker.model.Task;

import java.io.IOException;
import java.util.List;

public interface InputManager {
    int add();

    List<Task> list();

    void update();

    void save() throws IOException;

}
