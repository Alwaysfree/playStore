package com.player.dao;

import com.player.pojo.Product;

import java.util.List;

public interface ProductMapper{

    int update(Product product);

    int insert(Product product);

    int updateSeletive(Product product);

    Product selectById(Integer productId);

    List<Product> selectByNameAndId(String productName, Integer productId);

    List<Product> selectList();

    List<Product> selectByNameAndCategoryIds(Object p0, Object o);
}