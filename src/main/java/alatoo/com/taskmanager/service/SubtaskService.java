package alatoo.com.taskmanager.service;

import alatoo.com.taskmanager.entity.Subtask;
import alatoo.com.taskmanager.entity.Task;
import alatoo.com.taskmanager.model.SubtaskModel;

import java.util.List;
import java.util.Optional;

public interface SubtaskService {

    Long saveSubtask(SubtaskModel subtaskModel);

    Subtask findSubtaskById(Long subtaskId);

    List<Subtask> findAllSubtasksByTaskId(Long taskId);

    void deleteSubtaskById(Long subtaskId);

    void setIsCompleted(Long subtaskId);
}
