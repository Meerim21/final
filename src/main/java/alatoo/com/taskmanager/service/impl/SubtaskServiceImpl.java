package alatoo.com.taskmanager.service.impl;

import alatoo.com.taskmanager.entity.Subtask;
import alatoo.com.taskmanager.entity.Task;
import alatoo.com.taskmanager.enums.TaskStatus;
import alatoo.com.taskmanager.exceptions.BaseException;
import alatoo.com.taskmanager.model.SubtaskModel;
import alatoo.com.taskmanager.repo.SubtaskRepository;
import alatoo.com.taskmanager.service.SubtaskService;
import alatoo.com.taskmanager.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubtaskServiceImpl implements SubtaskService {

    SubtaskRepository subtaskRepository;
    TaskService taskService;

    @Override
    public Long saveSubtask(SubtaskModel subtaskModel) {
        Task task = taskService.findTaskById(subtaskModel.getTaskId());
        Subtask subtask = subtaskModel.getId() == null ? new Subtask() : findSubtaskById(subtaskModel.getId());
        subtask.setTask(task);
        subtask.setTitle(subtaskModel.getTitle());
        subtask.setDescription(subtaskModel.getDescription());
        return subtaskRepository.save(subtask).getId();
    }

    @Override
    public Subtask findSubtaskById(Long subtaskId) {
        return subtaskRepository.findById(subtaskId)
                .orElseThrow(() -> new BaseException(String.format("Subtask not found with id: %s", subtaskId)));
    }

    @Override
    public List<Subtask> findAllSubtasksByTaskId(Long taskId) {
        Task task = taskService.findTaskById(taskId);
        return subtaskRepository.findAllByTaskAndDeletedDateIsNull(task);
    }

    @Override
    public void deleteSubtaskById(Long subtaskId) {
        Subtask subtask = findSubtaskById(subtaskId);
        subtask.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        subtaskRepository.save(subtask);
    }

    @Transactional
    @Override
    public void setIsCompleted(Long subtaskId) {
        Subtask subtask = findSubtaskById(subtaskId);
        subtask.isCompleted();
        subtaskRepository.save(subtask);

        Task task = taskService.findTaskById(subtask.getTask().getId());
        List<Subtask> subtasks = findAllSubtasksByTaskId(task.getId());

        if(subtasks.size() > 0) {
            var completedSubtasks = subtasks.stream().filter(x -> x.isCompleted()).collect(Collectors.toList()).size();
            if(completedSubtasks == subtasks.size()) {
                task.setCompletedDate(new Timestamp(System.currentTimeMillis()));
                task.setStatus(TaskStatus.COMPLETED);
                taskService.saveTask(task);
            }else{
                task.setStatus(TaskStatus.IN_PROGRESS);
                taskService.saveTask(task);
            }
        }
    }


}
