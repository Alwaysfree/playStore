package com.player.controller.portal;

import com.player.common.Const;
import com.player.common.ResponseCode;
import com.player.common.ServerResponse;
import com.player.pojo.User;
import com.player.service.CartService;
import com.player.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/")
public class CartController{

    @Autowired
    private CartService cartService;


    /**
     * 获取购物车里的产品列表
     * @author alwaysfree
     * @date 2018/3/8 10:01
     * @param [session]
     * @return com.player.common.ServerResponse<com.player.vo.CartVo>
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<CartVo> list(HttpSession session){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
    return cartService.list(user.getId());
    }


    /**
     * 往购物车里添加产品
     * @author alwaysfree
     * @date 2018/3/8 10:06
     * @param [session, productId, count]
     * @return com.player.common.ServerResponse<com.player.vo.CartVo>
     */
    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse<CartVo> add(HttpSession session,Integer productId,Integer count){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.add(user.getId(),productId,count);
    }

    /**
     * 更新购物车里的产品
     * @author alwaysfree
     * @date 2018/3/8 10:08
     * @param [session]
     * @return com.player.common.ServerResponse<com.player.vo.CartVo>
     */
    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse<CartVo> update(HttpSession session,Integer productId,Integer count){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.update(user.getId(),productId,count);
    }


    /**
     * 删除购物车里的产品
     * @author alwaysfree
     * @date 2018/3/8 10:12
     * @param [session, productId, count]
     * @return com.player.common.ServerResponse<com.player.vo.CartVo>
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse<CartVo> delete(HttpSession session,String productIds){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.delete(user.getId(),productIds);
    }


    /**
     * 购物车里商品全选
     * @author alwaysfree
     * @date 2018/3/8 11:05
     * @param [session]
     * @return com.player.common.ServerResponse<com.player.vo.CartVo>
     */
    @RequestMapping("selectAll.do")
    @ResponseBody
    public ServerResponse<CartVo> selectAll(HttpSession session){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.selectOrNot(user.getId(),null,Const.Cart.CHECKED);
    }



    /**
     * 购物车里商品取消全选
     * @author alwaysfree
     * @date 2018/3/8 11:07
     * @param [session]
     * @return com.player.common.ServerResponse<com.player.vo.CartVo>
     */
    @RequestMapping("unSelectAll.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelectAll(HttpSession session){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.selectOrNot(user.getId(),null,Const.Cart.UN_CHECKED);
    }

    /**
     * 购物车里单选一件产品
     * @author alwaysfree
     * @date 2018/3/8 11:11
     * @param [session, productId, count]
     * @return com.player.common.ServerResponse<com.player.vo.CartVo>
     */
    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse<CartVo> select(HttpSession session,Integer productId){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.selectOrNot(user.getId(),productId,Const.Cart.CHECKED);
    }


    /**
     * 购物车里反选商品
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("unSelect.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelect(HttpSession session,Integer productId){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.selectOrNot(user.getId(),productId,Const.Cart.UN_CHECKED);
    }


    /**
     *
     * @author alwaysfree
     * @date 2018/3/8 12:38
     * @param [session, productId, count]
     * @return com.player.common.ServerResponse<com.player.vo.CartVo>
     */
    @RequestMapping("getCartProductCount.do")
    @ResponseBody
    public ServerResponse<Integer> getCartProductCount(HttpSession session,Integer productId,Integer count){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return cartService.getCartProductCount(user.getId());
    }

}



