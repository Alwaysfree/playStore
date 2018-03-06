package com.player.service;


import com.player.common.ServerResponse;
import com.player.vo.ProductDetailVo;

public interface ProjectService{
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

}