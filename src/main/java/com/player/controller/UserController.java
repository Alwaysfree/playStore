package com.player.controller;

import com.player.common.Const;
import com.player.common.ResponseCode;
import com.player.common.ServerResponse;
import com.player.pojo.User;
import com.player.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/user/")
public class  UserController{
    @Autowired
    private UserService userService;

    /**  
     *用户登录
     * @author alwaysfree 
     * @date 2018/3/2 14:30
     * @param [username, password, session]  
     * @return com.player.common.ServerResponse<com.player.pojo.User>  
     */  
    @RequestMapping(value="login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User>  response = userService.login(username, password);
        if(response.isSussess()){
            session.setAttribute(Const.currentUser,response.getDate());
        }
        return response;
    }

    /**
     *  用户注册
     * @author alwaysfree
     * @date 2018/3/2 14:39
     * @param [user]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value="register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
        ServerResponse<String> response = userService.register(user);
        return response;
    }

    /**
     *   用户登出
     * @author alwaysfree
     * @date 2018/3/2 14:42
     * @param [session]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value="logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.currentUser);
        return ServerResponse.createBySuccess();
    }

    /**
     *
     * @author alwaysfree
     * @date 2018/3/2 15:03
     * @param [str, type]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value="checkVaild.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkVaild(String str,String type){
        return userService.checkVlid(str,type);
    }

    /**
     * 获取用户信息
     * @author alwaysfree
     * @date 2018/3/2 15:47
     * @param [session]
     * @return com.player.common.ServerResponse<com.player.pojo.User>
     */
    @RequestMapping(value="getUserInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user!=null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

/**
 * 获取忘记密码的问题
 * @author alwaysfree
 * @date 2018/3/2 16:04
 * @param [question]
 * @return com.player.common.ServerResponse<java.lang.String>
 */
    @RequestMapping(value="forgetQuestion.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetQuestion(String username){
        return userService.selectQuestion(username);
    }

    /**
     * 检验问题的答案
     * @author alwaysfree
     * @date 2018/3/2 16:09
     * @param [username, question, answer]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value="forgetAnswer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetAnswer(String username,String question,String answer){
        return userService.checkAnswer(username,question,answer);
    }

    /**  
     * 忘记密码之后的密码重置
     * @author alwaysfree 
     * @date 2018/3/2 16:24  
     * @param [username, newPassword, token]  
     * @return com.player.common.ServerResponse<java.lang.String>  
     */  
    @RequestMapping(value="forgetResetPassword.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username,String newPassword,String token){
        return userService.forgetResetPassword(username,newPassword,token);
    }

    /**
     *用户在登录成功的情况下修改密码
     * @author alwaysfree
     * @date 2018/3/2 16:30
     * @param [username, newPassword, oldPassword]
     * @return com.player.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value="ResetPassword.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> ResetPassword(HttpSession session,String newPassword,String oldPassword){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(user,newPassword,oldPassword);
    }

    /**
     *  用户更新
     * @author alwaysfree
     * @date 2018/3/2 16:42
     * @param [session, user]
     * @return com.player.common.ServerResponse<com.player.pojo.User>
     */
    @RequestMapping(value="updateUserInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUserInfo(HttpSession session,User user){
        User currentUser = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());//用户id不更新
        user.setUsername(currentUser.getUsername());//用户名不可变更
        ServerResponse<User> response =  userService.updateInfomation(user);
        if (response.isSussess()){
            response.getDate().setUsername(currentUser.getUsername());
            session.setAttribute(Const.currentUser,response.getDate());
        }
        return response;
    }

    /**
     *获取登录用户的个人信息
     * @author alwaysfree
     * @date 2018/3/2 17:00
     * @param [session]
     * @return com.player.common.ServerResponse<com.player.pojo.User>
     */
    @RequestMapping(value="getUserInfomation.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfomation(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.currentUser);
        if (currentUser==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，需要强制登录");
        }
        return userService.getUserInfomation(currentUser.getId());
    }
}