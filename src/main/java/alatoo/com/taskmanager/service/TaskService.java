package alatoo.com.taskmanager.service;

import alatoo.com.taskmanager.entity.Task;
import alatoo.com.taskmanager.enums.TaskStatus;
import alatoo.com.taskmanager.model.TaskModel;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface TaskService {

    Long saveTaskModel(String username, TaskModel taskModel);

    Long saveTask(Task task);

    Task findTaskById(Long taskId);

    List<Task> findAllTasks();

    void deleteTaskById(String username, Long taskId);

    List<Task> findAllByUsernameAndDueDate(String username, Date dueDate);

    List<Task> findAllByUsername(String username);

    List<Task> findAll();

    List<Task> findAllByUsernameAndStatus(String username, TaskStatus status);
}
