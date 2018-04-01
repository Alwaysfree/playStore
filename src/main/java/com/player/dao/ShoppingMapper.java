package com.player.dao;

import com.player.pojo.Shopping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingMapper{

    int insert(Shopping shopping);

    int deleteByUserIdAndShoppingId(@Param("userId") Integer userId, @Param("shoppingId") Integer shoppingId);

    int updateByShopping(Shopping shopping);

    Shopping selectByUserIdAndShoppingId(@Param("userId") Integer id, @Param("shoppingId") Integer shoppingId);

    Shopping selectById(Integer id);

    List<Shopping> selectByUserId(Integer userId);
}