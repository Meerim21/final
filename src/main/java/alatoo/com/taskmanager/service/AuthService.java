package alatoo.com.taskmanager.service;

import alatoo.com.taskmanager.model.UserModel;

public interface AuthService {
    Long signup(UserModel input) ;
    String login(UserModel input) ;
}
