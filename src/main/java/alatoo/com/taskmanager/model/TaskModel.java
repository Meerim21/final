package alatoo.com.taskmanager.model;

import alatoo.com.taskmanager.enums.TaskPriority;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskModel {
    Long id;
    @NotEmpty(message = "Title cannot be empty")
    String title;
    @NotEmpty(message = "Description cannot be empty")
    String description;
    @NotEmpty(message = "Due date cannot be empty")
    Date dueDate;
    @NotEmpty(message = "Task priority cannot be empty")
    TaskPriority taskPriority;
    @NotNull(message = "Estimated hours cannot be empty")
    Integer estimatedHours;
}
