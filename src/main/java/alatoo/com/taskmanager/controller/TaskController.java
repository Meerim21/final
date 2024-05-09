package alatoo.com.taskmanager.controller;

import alatoo.com.taskmanager.entity.Task;
import alatoo.com.taskmanager.enums.TaskStatus;
import alatoo.com.taskmanager.exceptions.BaseException;
import alatoo.com.taskmanager.model.TaskModel;
import alatoo.com.taskmanager.response.CustomResponse;
import alatoo.com.taskmanager.response.ResultCode;
import alatoo.com.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @PostMapping
    public CustomResponse<Long> saveTask(@Valid @RequestBody TaskModel taskModel) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var username = authentication.getName();
            return new CustomResponse<>(taskService.saveTaskModel(username, taskModel), ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("TaskController : saveTask() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("TaskController : saveTask() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error saving task");
        }
    }

    @GetMapping("/{taskId}")
    public CustomResponse<Task> getTaskById(@PathVariable Long taskId) {
        try {
            return new CustomResponse<>(taskService.findTaskById(taskId), ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("TaskController : getTaskById() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("TaskController : getTaskById() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, e.getMessage());
        }
    }

    @GetMapping("/user/byDueDate")
    public CustomResponse<List<Task>> getTasksByUserAndDueDate(
            @RequestParam Date dueDate) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var username = authentication.getName();
            return new CustomResponse<>(taskService.findAllByUsernameAndDueDate(username, dueDate), ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("TaskController : getTasksByUserAndDueDate() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("TaskController : getTasksByUserAndDueDate() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, e.getMessage());
        }
    }

    @GetMapping("/user/byStatus")
    public CustomResponse<List<Task>> getTasksByUserAndStatus(
            @RequestParam TaskStatus status) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var username = authentication.getName();
            return new CustomResponse<>(taskService.findAllByUsernameAndStatus(username, status), ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("TaskController : getTasksByUserAndStatus() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("TaskController : getTasksByUserAndStatus() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, " Error getting tasks by user and status");
        }
    }

    @GetMapping("/user/tasks")
    public CustomResponse<List<Task>> getTasksByUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var username = authentication.getName();
            return new CustomResponse<>(taskService.findAllByUsername(username), ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("TaskController : getTasksByUser() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("TaskController : getTasksByUser() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error getting tasks by user");
        }
    }

    @GetMapping
    public CustomResponse<List<Task>> getAllTasks() {
        try {
            return new CustomResponse<>(taskService.findAllTasks(), ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("TaskController : getAllTasks() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("TaskController : getAllTasks() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error getting tasks");
        }
    }

    @DeleteMapping("/user/{taskId}")
    public CustomResponse<Object> deleteTask(@PathVariable Long taskId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var username = authentication.getName();
            taskService.deleteTaskById(username, taskId);
            return new CustomResponse<>("Successfully deleted", ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("TaskController : deleteTask() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("TaskController : deleteTask() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error deleting task");
        }
    }
}
