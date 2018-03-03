package com.player.controller.bankend;

import com.player.common.Const;
import com.player.common.ResponseCode;
import com.player.common.ServerResponse;
import com.player.pojo.User;
import com.player.service.CategoryService;
import com.player.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
public class CategoryManageController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    /**
     *  添加品类分类
     * @author alwaysfree
     * @date 2018/3/3 12:26
     * @param [name, parentId, session]
     * @return com.player.common.ServerResponse
     */
    @ResponseBody
    @RequestMapping(value = "addCategory.do")
    public ServerResponse addCategory(String name, @RequestParam(value = "parentId", defaultValue = "0") int parentId, HttpSession session) {
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //检验是否是管理员
        if (userService.checkAdminRole(user).isSussess()) {
            //如果是管理员，则增加分类
            return categoryService.addCategory(name, parentId);
        } else {
            return ServerResponse.createByErrorMessage("无管理员权限，需要管理员权限");
        }
    }

    /**  
     * 更新品类名
     * @author alwaysfree 
     * @date 2018/3/3 13:07
     * @param [categoryName, categoryId, session]  
     * @return com.player.common.ServerResponse  
     */  
    @ResponseBody
    @RequestMapping(value = "setCategoryName.do")
    public ServerResponse setCategoryName(String categoryName, Integer categoryId, HttpSession session) {
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //检验是否是管理员
        if (userService.checkAdminRole(user).isSussess()) {
            //如果是管理员，则更新品类名
            return categoryService.updateCategory(categoryId,categoryName);
        } else {
            return ServerResponse.createByErrorMessage("无管理员权限，需要管理员权限");
        }
    }


    /**
     * 查询当前节点的子节点的category,不递归保持平级
     * @author alwaysfree
     * @date 2018/3/3 13:18
     * @param [name, categoryId, session]
     * @return com.player.common.ServerResponse
     */
    @ResponseBody
    @RequestMapping(value = "getCategory.do")
    public ServerResponse getCategory(String name, @RequestParam(value = "categoryId", defaultValue = "0") int categoryId, HttpSession session) {
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //检验是否是管理员
        if (userService.checkAdminRole(user).isSussess()) {
            //如果是管理员，则查询当前节点的子节点的category,不递归保持平级
            return categoryService.getChildrenParallelCategory(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无管理员权限，需要管理员权限");
        }
    }

    /**  
     * 查询当前节点的子节点和所有子节点的category
     * @author alwaysfree 
     * @date 2018/3/3 13:21  
     * @param [name, categoryId, session]  
     * @return com.player.common.ServerResponse  
     */  
    @ResponseBody
    @RequestMapping(value = "getDeepCategory.do")
    public ServerResponse getDeepCategory(String name, @RequestParam(value = "categoryId", defaultValue = "0") int categoryId, HttpSession session) {
        User user = (User) session.getAttribute(Const.currentUser);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //检验是否是管理员
        if (userService.checkAdminRole(user).isSussess()) {
            //如果是管理员，则查询当前节点的子节点和所有子节点的category
            return categoryService.selectChildrenAndCategoryById(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无管理员权限，需要管理员权限");
        }
    }

}




