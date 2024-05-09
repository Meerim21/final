package alatoo.com.taskmanager.controller;

import alatoo.com.taskmanager.entity.Subtask;
import alatoo.com.taskmanager.exceptions.BaseException;
import alatoo.com.taskmanager.model.SubtaskModel;
import alatoo.com.taskmanager.response.CustomResponse;
import alatoo.com.taskmanager.response.ResultCode;
import alatoo.com.taskmanager.service.SubtaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/subtasks")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubtaskController {

    SubtaskService subtaskService;

    @PostMapping
    public CustomResponse<Long> saveSubtask(@Valid @RequestBody SubtaskModel subtaskModel) {
        try{
            return new CustomResponse<>(subtaskService.saveSubtask(subtaskModel), ResultCode.SUCCESS);
        }catch (BaseException e){
            log.error("SubtaskController : saveSubtask : ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        }catch (Exception e){
            log.error("SubtaskController : saveSubtask : ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error saving subtask");
        }
    }

    @GetMapping("/{subtaskId}")
    public CustomResponse<Subtask> getSubtaskById(@PathVariable Long subtaskId) {
        try {
            return new CustomResponse<>(subtaskService.findSubtaskById(subtaskId), ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("SubtaskController : getSubtaskById() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("SubtaskController : getSubtaskById() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error getting subtask by id");
        }
    }

    @GetMapping("/task/{taskId}")
    public CustomResponse<List<Subtask>> getAllSubtasksByTask(@PathVariable Long taskId) {
        try {
            return new CustomResponse<>(subtaskService.findAllSubtasksByTaskId(taskId), ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("SubtaskController : getSubtaskById() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("SubtaskController : getSubtaskById() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error getting all subtasks by task id");
        }
    }

    @DeleteMapping("/{subtaskId}")
    public CustomResponse<Object> deleteSubtask(@PathVariable Long subtaskId) {
        try {
            subtaskService.deleteSubtaskById(subtaskId);
            return new CustomResponse<>("Successfully deleted", ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("SubtaskController : deleteSubtask() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("SubtaskController : deleteSubtask() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error deleting subtask");
        }
    }

    @PutMapping("/complete/{subtaskId}")
    public CustomResponse<Object> completeSubtask(@PathVariable Long subtaskId) {
        try {
            subtaskService.setIsCompleted(subtaskId);
            return new CustomResponse<>("Successfully completed", ResultCode.SUCCESS);
        } catch (BaseException e) {
            log.error("SubtaskController : completeSubtask() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error("SubtaskController : completeSubtask() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error completing subtask");
        }
    }
}
