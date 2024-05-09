package alatoo.com.taskmanager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "subtasks")
public class Subtask{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    boolean isCompleted;
    @ManyToOne
    @JoinColumn
    Task task;
    @CreationTimestamp
    @Column(updatable = false)
    Timestamp createdDate;
    @UpdateTimestamp
    Timestamp updatedDate;
    Timestamp deletedDate;
}
