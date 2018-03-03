package  com.player.controller.bankend;

import com.player.common.Const;
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
@RequestMapping("/manage/user/")
public class UserManageController{
    @Autowired
    private UserService userService;

    /**
     *  管理员登录
     * @author alwaysfree
     * @date 2018/3/3 10:48
     * @param [username, password, session]
     * @return com.player.common.ServerResponse<com.player.pojo.User>
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response= userService.login(username,password);
        if(response.isSussess()){
            User user = response.getDate();
            if(user.getRole()== Const.Role.ROLE_CUSTOMER){
                //管理员登录成功
                session.setAttribute(Const.currentUser,user);
                return response;
            }else {
                return ServerResponse.createByErrorMessage("不是管理员账号，请用管理员账号登录");
            }
        }
        return response;
    }
}