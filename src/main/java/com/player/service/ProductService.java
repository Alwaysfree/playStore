package com.player.service;


import com.github.pagehelper.PageInfo;
import com.player.common.ServerResponse;
import com.player.pojo.Product;
import com.player.vo.ProductDetailVo;
import org.springframework.stereotype.Service;

@Service
public interface ProductService{
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);
    ServerResponse<PageInfo> getProductByCategoryKeyword(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse productSearch(String productName,Integer productId,int pageNum,int pageSize);
    ServerResponse SetSaleStatus(Integer productI,Integer status);
    ServerResponse getList(int pageNum,int pageSize);

}