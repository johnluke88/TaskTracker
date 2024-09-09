package com.tasktracker.repo;

import com.tasktracker.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface TaskRepo {

    void saveToJson(String path, ArrayList<Task> list) throws IOException;

    ArrayList<Task> uploadFromJson(String path) throws IOException;
}
