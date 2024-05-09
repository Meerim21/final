package alatoo.com.taskmanager.entity;

import alatoo.com.taskmanager.enums.TaskPriority;
import alatoo.com.taskmanager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    Timestamp completedDate;
    Date dueDate;
    Integer estimatedHours;
    @Enumerated(EnumType.STRING)
    TaskPriority priority;
    @Enumerated(EnumType.STRING)
    TaskStatus status;
    @ManyToOne
    @JoinColumn
    User user;
    @CreationTimestamp
    @Column(updatable = false)
    Timestamp createdDate;
    @UpdateTimestamp
    Timestamp updatedDate;
    Timestamp deletedDate;
}
