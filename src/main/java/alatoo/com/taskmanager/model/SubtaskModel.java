package alatoo.com.taskmanager.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubtaskModel {
    Long id;
    @NotEmpty(message = "Title cannot br empty")
    String title;
    @NotEmpty(message = "Description cannot be empty")
    String description;
    @NotNull(message = "Task id cannot be null")
    Long taskId;
}
