package com.player.dao;

import com.player.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper{

    int update(Product product);

    int insert(Product product);

    int updateSeletive(Product product);

    Product selectById(Integer id);

    List<Product> selectByNameAndId(@Param("productName") String productName, @Param("productId") Integer productId);

    List<Product> selectList();

    List<Product> selectByNameAndCategoryIds(@Param("productName")String productName,@Param("categoryIdList")List<Integer> categoryIdList);
}