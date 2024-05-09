package alatoo.com.taskmanager.exceptions;

import alatoo.com.taskmanager.response.CustomResponse;
import alatoo.com.taskmanager.response.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomResponse<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new CustomResponse<>(null, ResultCode.EXCEPTION, errors.toString());
    }

    @ExceptionHandler(BaseException.class)
    public CustomResponse<Object> handleNotFoundException(BaseException ex) {
        return new CustomResponse<>(null, ResultCode.EXCEPTION);
    }

}
