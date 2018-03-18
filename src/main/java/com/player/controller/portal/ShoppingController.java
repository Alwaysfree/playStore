package com.player.controller.portal;

import com.github.pagehelper.PageInfo;
import com.player.common.Const;
import com.player.common.ResponseCode;
import com.player.common.ServerResponse;
import com.player.pojo.Shopping;
import com.player.pojo.User;
import com.player.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/shopping/")
public class ShoppingController{

    @Autowired
    private ShoppingService shoppingService;

    /**
     * 添加用户收货地址
     * @param session
     * @param shopping
     * @return
     */
    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session,Shopping shopping){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
            return shoppingService.add(user.getId(),shopping);
    }

    /**
     * 删除用户收货地址
     * @param session
     * @param shoppingId
     * @return
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse delete(HttpSession session,Integer shoppingId){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return shoppingService.delete(user.getId(),shoppingId);
    }

    /**
     * 更新用户地址
     * @param session
     * @param shopping
     * @return
     */
    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session,Shopping shopping){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return shoppingService.update(user.getId(),shopping);
    }

    /**
     * 查看用户某个地址
     * @param session
     * @param shoppingId
     * @return
     */
    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse select(HttpSession session,Integer shoppingId){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return shoppingService.select(user.getId(),shoppingId);
    }


    @RequestMapping("selectAll.do")
    @ResponseBody
    public ServerResponse<PageInfo> selectAll(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return shoppingService.selectAll(user.getId(),pageNum,pageSize);
    }



}