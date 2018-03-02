package com.player.service;

import com.player.common.ServerResponse;
import com.player.pojo.User;

public interface UserService{
    ServerResponse<User> login(String username,String password);
    ServerResponse<String> register(User user);
    ServerResponse<String> checkVlid(String str,String type);
    ServerResponse selectQuestion(String username);
    ServerResponse<String> checkAnswer(String username,String question,String answer);
    ServerResponse<String> forgetResetPassword(String username,String newPassword,String token);
    ServerResponse<String> resetPassword(User user,String newPassword,String oldPassword);
    ServerResponse<User> updateInfomation(User user);
    ServerResponse<User> getUserInfomation(Integer userId);
    ServerResponse checkAdminRole(User user);
}