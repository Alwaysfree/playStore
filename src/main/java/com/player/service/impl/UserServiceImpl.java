package  com.player.service.impl;

import com.player.common.Const;
import com.player.common.ServerResponse;
import com.player.common.TokenCache;
import com.player.dao.UserMapper;
import com.player.pojo.User;
import com.player.service.UserService;
import com.player.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override

    /**
     * 用户登录的逻辑实现
     * @author alwaysfree
     * @date 2018/3/6 10:03
     * @param [username, password]
     * @return com.player.common.ServerResponse<com.player.pojo.User>
     */
    public ServerResponse<User> login(String username, String password) {
        int resultCode = userMapper.checkUsername(username);
        if(resultCode == 0){
            ServerResponse.createByErrorMessage("用户名不存在");
        }
        String MD5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.checkLogin(username,MD5Password);
        if (user==null){
            ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功",user);
    }

    @Override
    /**
     * 用戶注冊的逻辑实现
     * @author alwaysfree
     * @date 2018/3/6 10:26
     * @param [user]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    public ServerResponse<String> register(User user) {
    ServerResponse response = checkVlid(user.getUsername(), Const.currentUser);
    if(!response.isSussess()){
        return response;
    }
    response = checkVlid(user.getEmail(),Const.EMAIL);
    if (!response.isSussess()){
        return response;
    }
    user.setRole(Const.Role.ROLE_CUSTOMER);
    user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
    int resultCode = userMapper.addUser(user);
    if(resultCode==0){
        ServerResponse.createByErrorMessage("注冊失敗");
    }
        return ServerResponse.createByMassage("注冊成功");
    }

    @Override
    /**
     * 校验用户的用户名和邮箱是否已存在
     * @author alwaysfree
     * @date 2018/3/6 10:40
     * @param [str, type]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    public ServerResponse<String> checkVlid(String str, String type) {
        if (StringUtils.isNotBlank(type)){
        //开始校验
            int resultCode = userMapper.checkUsername(type);
            if (resultCode>0){
                return ServerResponse.createByErrorMessage("用户名已存在");
            }
            resultCode = userMapper.checkEmail(type);
            if(resultCode>0){
                return ServerResponse.createByErrorMessage("邮箱已存在");
            }
        }else{
            return ServerResponse.createByErrorMessage("参数校验失败");
        }
        return ServerResponse.createByMassage("参数校验成功");
    }

    @Override
    /**
     *  查找密保对应的问题
     * @author alwaysfree
     * @date 2018/3/6 11:09
     * @param [username]
     * @return com.player.common.ServerResponse
     */
    public ServerResponse selectQuestion(String username) {
        //检验用户名对应的用户是否已存在
        ServerResponse response = checkVlid(username,Const.USERNAME);
        if (response.isSussess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)){
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题为空");
    }

    @Override
    /**
     * 校验密保答案是否正确
     * @author alwaysfree
     * @date 2018/3/6 11:20
     * @param [username, question, answer]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCode = userMapper.checkAnswer(username,question,answer);
        if (resultCode>0){
            //密保对应的问题答案是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("密保答案错误");
    }

    @Override
    /**
     * 忘记密码时重置密码的逻辑实现
     * @author alwaysfree
     * @date 2018/3/6 11:53
     * @param [username, newPassword, forgetToken]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    public ServerResponse<String> forgetResetPassword(String username, String newPassword, String forgetToken) {
        //检查token是否存在
        if (StringUtils.isBlank(forgetToken)){
            ServerResponse.createByErrorMessage("token参数错误");
        }
        //检查用户是否存在
        ServerResponse response = checkVlid(username,Const.USERNAME);
        if (response.isSussess()){
            //用户不存在
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        //检验toktn
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if (StringUtils.isBlank(token)){
            ServerResponse.createByErrorMessage("toktn已过期");
        }
        if (StringUtils.equals(token,forgetToken)){
            String MD5Password = MD5Util.MD5EncodeUtf8(newPassword);
            int resultCode = userMapper.udatePasswordByUsername(username,newPassword);
            if (resultCode>0){
                return  ServerResponse.createByMassage("密码重置成功");
            }
        }else{
                return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
            }
        return ServerResponse.createByErrorMessage("密码重置失败");
    }

    @Override
    /**
     *  登录成功以后的
     * @author alwaysfree
     * @date 2018/3/6 13:46
     * @param [user, newPassword, oldPassword]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    public ServerResponse<String> resetPassword(User user, String newPassword, String oldPassword) {
        //检验用户的旧密码
        int resultCode = userMapper.checkPassword(oldPassword,user.getPassword());
        if (resultCode==0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(newPassword));
        resultCode = userMapper.updateByPrimaryKey(user);
        if (resultCode>0){
            return ServerResponse.createBySuccess("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    /**
     * 更新个人信息
     * @author alwaysfree
     * @date 2018/3/6 14:25
     * @param [user]
     * @return com.player.common.ServerResponse<com.player.pojo.User>
     */
    public ServerResponse<User> updateInfomation(User user) {
        //检验email是否已存在,如果存在则不能是当前用户
        int resultCode = userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if (resultCode>0) {
            return ServerResponse.createByErrorMessage("当前邮箱已经存在，请更换邮箱");
        }
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setEmail(user.getEmail());
            newUser.setPhone(user.getPhone());
            newUser.setQuestion(user.getQuestion());
            newUser.setAnswer(user.getAnswer());
        int updateCode = userMapper.updateByPrimaryKey(user);
        if (updateCode>0){
            return ServerResponse.createBySuccess("更新个人信息成功",newUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    @Override
    /**
     * 获取用户的个人信息
     * @author alwaysfree
     * @date 2018/3/6 14:43
     * @param [userId]
     * @return com.player.common.ServerResponse<com.player.pojo.User>
     */
    public ServerResponse<User> getUserInfomation(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user==null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }



    @Override
    /**
     * 检验是否是管理员
     * @author alwaysfree
     * @date 2018/3/6 14:49
     * @param [user]
     * @return com.player.common.ServerResponse
     */
    public ServerResponse checkAdminRole(User user) {
        if (user!=null && user.getRole().intValue()==Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }
}