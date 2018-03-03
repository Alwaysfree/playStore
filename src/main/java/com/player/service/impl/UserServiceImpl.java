package  com.player.service.impl;

import com.player.common.ServerResponse;
import com.player.pojo.User;
import com.player.service.UserService;

public class UserServiceImpl implements UserService{

    @Override
    public ServerResponse<User> login(String username, String password) {
        return null;
    }

    @Override
    public ServerResponse<String> register(User user) {
        return null;
    }

    @Override
    public ServerResponse<String> checkVlid(String str, String type) {
        return null;
    }

    @Override
    public ServerResponse selectQuestion(String username) {
        return null;
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        return null;
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String newPassword, String token) {
        return null;
    }

    @Override
    public ServerResponse<String> resetPassword(User user, String newPassword, String oldPassword) {
        return null;
    }

    @Override
    public ServerResponse<User> updateInfomation(User user) {
        return null;
    }

    @Override
    public ServerResponse<User> getUserInfomation(Integer userId) {
        return null;
    }

    @Override
    public ServerResponse checkAdminRole(User user) {
        return null;
    }
}