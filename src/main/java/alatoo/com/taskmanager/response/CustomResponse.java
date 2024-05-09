package alatoo.com.taskmanager.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomResponse<T> {
    T result;
    ResultCode resultCode;
    String message;

    public CustomResponse() {}

    public CustomResponse(T result, ResultCode resultCode) {
        this.result = result;
        this.resultCode = resultCode;
    }

    public CustomResponse(T result, ResultCode resultCode, String message) {
        this.result = result;
        this.resultCode = resultCode;
        this.message = message;
    }
}
