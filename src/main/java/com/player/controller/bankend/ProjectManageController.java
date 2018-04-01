package com.player.controller.bankend;

import com.google.common.collect.Maps;
import com.player.common.Const;
import com.player.common.ResponseCode;
import com.player.common.ServerResponse;
import com.player.pojo.Product;
import com.player.pojo.User;
import com.player.service.FileService;
import com.player.service.ProductService;
import com.player.service.UserService;
import com.player.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/manage/product/")
public class ProjectManageController{
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;

    /**
     * 管理员添加或者更新产品
     * @author alwaysfree
     * @date 2018/3/7 10:18
     * @param [session, product]
     * @return com.player.common.ServerResponse
     */
    @RequestMapping("sava.do")
    @ResponseBody
    public ServerResponse addProduct(HttpSession session,Product product){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请及时登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            //是管理员账号,则添加产品或者更新产品
            return productService.saveOrUpdateProduct(product);
        }else{
            return ServerResponse.createByErrorMessage("无权限添加产品");
        }
    }

    /**
     * 管理员根据产品名，产品id搜索产品
     * @author alwaysfree
     * @date 2018/3/7 12:08
     * @param [session, productName, productId, pageNum, pageSize]
     * @return com.player.common.ServerResponse
     */
    @RequestMapping("productSearch.do")
    @ResponseBody
    public ServerResponse productSearch(HttpSession session, String productName, Integer productId,
                                        @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请及时登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            //是管理员账号,进行产品搜索
            return productService.productSearch(productName,productId,pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    /**
     * 管理员用户设置产品上下架
     * @author alwaysfree
     * @date 2018/3/7 12:18
     * @param [session, productId, status]
     * @return com.player.common.ServerResponse
     */
    @RequestMapping("setSaleStatus.do")
    @ResponseBody
    public ServerResponse<String> setSaleStatus(HttpSession session, Integer productId,Integer status){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请及时登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            //是管理员账号,则设置产品上下架
            return productService.SetSaleStatus(productId,status);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    /**
     * 管理员获取产品的详细信息
     * @author alwaysfree
     * @date 2018/3/7 12:21
     * @param [session, productId]
     * @return com.player.common.ServerResponse
     */
    @RequestMapping("getDetail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请及时登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            //是管理员账号,则获取产品的详细信息
            return productService.manageProductDetail(productId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    /**
     * 管理员获取产品列表
     * @author alwaysfree
     * @date 2018/3/7 12:32
     * @param [session, productId]
     * @return com.player.common.ServerResponse
     */
    @RequestMapping("getProductList.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session,
                                  @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请及时登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            //是管理员账号,则获取产品列表
            return productService.getList(pageNum,pageSize);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    /**
     *上传图片
     * @param session
     * @param multipartFile
     * @param request
     * @return
     */
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false)MultipartFile multipartFile,
                                 HttpServletRequest request){
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员账号");
        }
        if (userService.checkAdminRole(user).isSussess()){
            //获取当前路径下名为“upload”的文件夹
            String path = request.getSession().getServletContext().getRealPath("upload");
            //获取path下的文件名
            String targetFileName = fileService.upload(multipartFile,path);
            //获取文件名的链接
            String  url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return ServerResponse.createBySuccess(fileMap);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    /**
     * 富文本上传图片
     * @param session
     * @param multipartFile
     * @param request
     * @return
     */
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map ImgUpload(HttpSession session, @RequestParam(value = "upload_file",required = false)MultipartFile multipartFile,
                                 HttpServletRequest request,HttpServletResponse response){
        //      {
        //            "success": true/false,
        //                "msg": "error message", # optional
        //            "file_path": "[real file path]"
        //        }
        Map map = Maps.newHashMap();
        User user = (User) session.getAttribute(Const.currentUser);
        if (user==null){
            map.put("success",false);
            map.put("msg","用户未登录，请登录管理员账号");
            return map;
        }
        if (userService.checkAdminRole(user).isSussess()){
            //获取当前路径下名为“upload”的文件夹
            String path = request.getSession().getServletContext().getRealPath("upload");
            //获取path下的文件名
            String targetFileName = fileService.upload(multipartFile,path);
            if (StringUtils.isBlank(targetFileName)){
                map.put("success",false);
                map.put("msg","上传失败");
                return map;
            }
            //获取文件名的链接
            String  url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
            map.put("success",true);
            map.put("msg","上传成功");
            map.put("file_path",url);
            response.addHeader("Access-Control-Allow-Headers","X-File-Name");
            return map;
        }else{
            map.put("success",false);
            map.put("msg","无权限操作");
            return map;
        }
    }
}
