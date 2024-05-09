package alatoo.com.taskmanager.repo;

import alatoo.com.taskmanager.entity.Subtask;
import alatoo.com.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    List<Subtask> findAllByTaskAndDeletedDateIsNull(Task task);
    Optional<Subtask> findById(Long id);
}
