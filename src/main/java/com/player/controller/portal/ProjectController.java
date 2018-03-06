package com.player.controller.portal;

import com.player.common.ServerResponse;
import com.player.service.ProjectService;
import com.player.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/")
public class ProjectController{
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/detail.do")
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return projectService.getProductDetail(productId);
    }



}