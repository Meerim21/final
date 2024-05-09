package alatoo.com.taskmanager.controller;

import alatoo.com.taskmanager.entity.User;
import alatoo.com.taskmanager.exceptions.BaseException;
import alatoo.com.taskmanager.model.UserModel;
import alatoo.com.taskmanager.response.CustomResponse;
import alatoo.com.taskmanager.response.ResultCode;
import alatoo.com.taskmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public CustomResponse<Long> saveUser(@Valid @RequestBody UserModel userModel) {
        try{
            return new CustomResponse<>(userService.saveUser(userModel), ResultCode.SUCCESS);
        }catch (BaseException e){
            log.error("UserController : saveUser() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        } catch (Exception e){
            log.error("UserController : saveUser() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error saving user");
        }
    }

    @GetMapping("/{userId}")
    public CustomResponse<User> getUserById(@PathVariable Long userId) {
        try {
            return new CustomResponse<>(userService.findUserById(userId), ResultCode.SUCCESS);
        }catch (BaseException e){
            log.error("UserController : getUserById() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        }catch (Exception e){
            log.error("UserController : getUserById() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error getting user by id");
        }
    }

    @GetMapping("/byUsername")
    public CustomResponse<User> getUserByUsername(@RequestParam String username) {
        try {
            return new CustomResponse<>(userService.findUserByUsername(username), ResultCode.SUCCESS);
        }catch (BaseException e){
            log.error("UserController : getUserByUsername() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        }catch (Exception e){
            log.error("UserController : getUserByUsername() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error getting user by name");
        }
    }

    @GetMapping
    public CustomResponse<List<User>> getAllUsers() {
        try {
            return new CustomResponse<>(userService.findAllUsers(), ResultCode.SUCCESS);
        }catch (BaseException e){
            log.error("UserController : getAllUsers() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        }catch (Exception e){
            log.error("UserController : getAllUsers() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error getting users");
        }
    }

    @DeleteMapping("/{userId}")
    public CustomResponse<Object> deleteUser(@PathVariable Long userId) {
        try {
            return new CustomResponse<>("Successfully deleted", ResultCode.SUCCESS);
        }catch (BaseException e){
            log.error("UserController : deleteUser() ", e);
            return new CustomResponse<>(null, ResultCode.EXCEPTION, e.getMessage());
        }catch (Exception e){
            log.error("UserController : deleteUser() ", e);
            return new CustomResponse<>(null, ResultCode.ERROR, "Error deleting user");
        }
    }
}
