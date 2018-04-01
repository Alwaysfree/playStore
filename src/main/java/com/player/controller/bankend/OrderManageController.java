package com.player.controller.bankend;

import com.github.pagehelper.PageInfo;
import com.player.common.Const;
import com.player.common.ResponseCode;
import com.player.common.ServerResponse;
import com.player.pojo.User;
import com.player.service.OrderService;
import com.player.service.UserService;
import com.player.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/order/")
public class OrderManageController{
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    /**
     * 管理员获取订单列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            return orderService.manageList(pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 管理员获取订单详情
     * @param session
     * @param orderNo
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> detail(HttpSession session, Long orderNo){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            return orderService.manageDetail(orderNo);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 管理员搜索订单列表
     * @param session
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> search(HttpSession session, Long orderNo,
                                           @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            return orderService.manageSearchl(orderNo,pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    /**
     * 管理员获取订单发送
     * @param session
     * @param orderNo
     * @return
     */
    @RequestMapping("send.do")
    @ResponseBody
    public ServerResponse<String> send(HttpSession session, Long orderNo){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            return orderService.manageSend(orderNo);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}