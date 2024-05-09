package alatoo.com.taskmanager.service.impl;

import alatoo.com.taskmanager.entity.User;
import alatoo.com.taskmanager.exceptions.BaseException;
import alatoo.com.taskmanager.model.UserModel;
import alatoo.com.taskmanager.repo.UserRepository;
import alatoo.com.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long saveUser(UserModel userModel) {
        if(checkUsername(userModel.getUsername())){
            throw new BaseException("User already exists");
        }
        User user = userModel.getId() == null ? new User() : findUserById(userModel.getId());
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setEmail(userModel.getEmail());
        return userRepository.save(user).getId();
    }

    private boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isEmpty() ? false : true;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(String.format("User not found by id : %s", userId)));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(String.format("User not found with username: %s ", username)));
    }

    @Override
    public void deleteUserById(Long userId) {
       User user = findUserById(userId);
       user.setDeletedDate(new Timestamp(System.currentTimeMillis()));
       userRepository.save(user);
    }
}
