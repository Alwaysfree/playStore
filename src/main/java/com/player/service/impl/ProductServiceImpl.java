package com.player.service.impl;

import com.github.pagehelper.PageInfo;
import com.player.common.ServerResponse;
import com.player.pojo.Product;
import com.player.service.ProductService;
import com.player.vo.ProductDetailVo;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId) {
        return null;
    }

    @Override
    public ServerResponse<PageInfo> getProductByCategoryKeyword(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy) {
        return null;
    }

    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        return null;
    }

    @Override
    public ServerResponse productSearch(String productName, Integer productId, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public ServerResponse SetSaleStatus(Integer productI, Integer status) {
        return null;
    }

    @Override
    public ServerResponse getList(int pageNum, int pageSize) {
        return null;
    }
}