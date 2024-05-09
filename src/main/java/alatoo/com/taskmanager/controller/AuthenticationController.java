package alatoo.com.taskmanager.controller;

import alatoo.com.taskmanager.model.UserModel;
import alatoo.com.taskmanager.response.CustomResponse;
import alatoo.com.taskmanager.response.ResultCode;
import alatoo.com.taskmanager.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthServiceImpl authServiceImpl;

    @PostMapping("/signup")
    public CustomResponse<Long> signUp(@Valid @RequestBody UserModel userModel) {
        try {
            return new CustomResponse<>(authServiceImpl.signup(userModel), ResultCode.SUCCESS);
        }  catch (Exception e) {
            log.error("AuthenticationController : signUp() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, e.getMessage());
        }
    }

    @PostMapping("/login")
    public CustomResponse<String> authenticate(@Valid @RequestBody UserModel loginUserDto) {
        try {
            return new CustomResponse<>(authServiceImpl.login(loginUserDto), ResultCode.SUCCESS);
        } catch (Exception e) {
            log.error("AuthenticationController : authenticate() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, e.getMessage());
        }
    }
}