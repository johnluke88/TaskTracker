package com.tasktracker.model;

import java.util.Objects;

public class Task {

    private int id;
    private String description;
    private String status;
    private String createdAt;
    private String updateAt;

    public Task(int id, String description, String status, String createdAt, String updateAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(description, task.description) && Objects.equals(status, task.status) && Objects.equals(createdAt, task.createdAt) && Objects.equals(updateAt, task.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status, createdAt, updateAt);
    }
}
