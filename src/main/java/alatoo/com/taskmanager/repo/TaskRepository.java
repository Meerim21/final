package alatoo.com.taskmanager.repo;

import alatoo.com.taskmanager.entity.Task;
import alatoo.com.taskmanager.entity.User;
import alatoo.com.taskmanager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserAndCreatedDate(User user, Timestamp createdDate);
    List<Task> findAllByUserAndDueDateAndDeletedDateIsNull(User user, Date dueDate);
    List<Task> findAllByUserAndDeletedDateIsNull(User user);
    List<Task> findAllByDeletedDateIsNull();
    List<Task> findAllByUserAndStatusAndDeletedDateIsNull(User user, TaskStatus status);
}
