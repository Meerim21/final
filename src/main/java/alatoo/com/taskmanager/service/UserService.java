package alatoo.com.taskmanager.service;

import alatoo.com.taskmanager.entity.User;
import alatoo.com.taskmanager.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Long saveUser(UserModel userModel);

    User findUserById(Long userId);

    List<User> findAllUsers();

    User findUserByUsername(String username);

    void deleteUserById(Long userId);
}
