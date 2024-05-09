package alatoo.com.taskmanager.service.impl;

import alatoo.com.taskmanager.entity.User;
import alatoo.com.taskmanager.model.UserModel;
import alatoo.com.taskmanager.security.JwtService;
import alatoo.com.taskmanager.service.AuthService;
import alatoo.com.taskmanager.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserService userService;

    PasswordEncoder passwordEncoder;

    AuthenticationManager authenticationManager;

    JwtService jwtService;

    public Long signup(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userService.saveUser(userModel);
    }

    public String login(UserModel userModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userModel.getUsername(),
                        userModel.getPassword()
                )
        );

        User user = userService.findUserByUsername(userModel.getUsername());

        return jwtService.generateToken(user);
    }
}