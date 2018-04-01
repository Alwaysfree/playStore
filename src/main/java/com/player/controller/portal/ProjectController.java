package com.player.controller.portal;

import com.github.pagehelper.PageInfo;
import com.player.common.ServerResponse;
import com.player.service.ProductService;
import com.player.vo.ProductDetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product/")
public class ProjectController{
    @Autowired
    private ProductService productService;

    /**  
     *      
     * @author alwaysfree 
     * @date 2018/3/6 23:50  
     * @param [productId]  
     * @return com.player.common.ServerResponse<com.player.vo.ProductDetailVo>  
     */  
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return productService.getProductDetail(productId);
    }

    
    /**  
     *      
     * @author alwaysfree 
     * @date 2018/3/6 23:51
     * @param [keyword, categoryId, pageNum, pageSize, orderBy]  
     * @return com.player.common.ServerResponse<com.github.pagehelper.PageInfo>  
     */  
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "")String orderBy){
        return productService.getProductByCategoryKeyword(keyword,categoryId,pageNum,pageSize,orderBy);
    }
}