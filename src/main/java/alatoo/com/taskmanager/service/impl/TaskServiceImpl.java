package alatoo.com.taskmanager.service.impl;

import alatoo.com.taskmanager.entity.Task;
import alatoo.com.taskmanager.entity.User;
import alatoo.com.taskmanager.enums.TaskStatus;
import alatoo.com.taskmanager.exceptions.BaseException;
import alatoo.com.taskmanager.model.TaskModel;
import alatoo.com.taskmanager.repo.TaskRepository;
import alatoo.com.taskmanager.service.TaskService;
import alatoo.com.taskmanager.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    UserService userService;

    @Override
    public Long saveTaskModel(String username, TaskModel taskModel) {
        Task task = taskModel.getId() == null ? new Task() : findTaskById(taskModel.getId());

        User user = userService.findUserByUsername(username);
        if(taskModel.getId() != null && !task.getUser().equals(user)){
            throw new BaseException(String.format("Client is not owner of task with id: %s", taskModel.getId()));
        }

        task.setUser(user);
        task.setTitle(taskModel.getTitle());
        task.setDescription(taskModel.getDescription());
        task.setDueDate(taskModel.getDueDate());
        task.setPriority(taskModel.getTaskPriority());
        task.setEstimatedHours(taskModel.getEstimatedHours());
        task.setStatus(task.getStatus() == null ? TaskStatus.NEW : task.getStatus());
        return taskRepository.save(task).getId();
    }

    @Override
    public Long saveTask(Task task) {
        return taskRepository.save(task).getId();
    }


    @Override
    public Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException(String.format("Task not found with id: %s", taskId)));
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTaskById(String username, Long taskId) {
        User user = userService.findUserByUsername(username);
        Task task = findTaskById(taskId);
        if(!task.getUser().equals(user)){
            throw new BaseException(String.format("Client is not owner of task with id: %s", taskId));
        }
        task.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        taskRepository.save(task);
    }


    @Override
    public List<Task> findAllByUsernameAndDueDate(String username, Date dueDate) {
        User user = userService.findUserByUsername(username);
        return taskRepository.findAllByUserAndDueDateAndDeletedDateIsNull(user, dueDate);
    }

    @Override
    public List<Task> findAllByUsername(String username) {
        User user = userService.findUserByUsername(username);
        return taskRepository.findAllByUserAndDeletedDateIsNull(user);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAllByDeletedDateIsNull();
    }

    @Override
    public List<Task> findAllByUsernameAndStatus(String username, TaskStatus status) {
        User user = userService.findUserByUsername(username);
        return taskRepository.findAllByUserAndStatusAndDeletedDateIsNull(user, status);
    }
}
