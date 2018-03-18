package com.player.dao;

import com.player.pojo.Shopping;

import java.util.List;

public interface ShoppingMapper{

    public int insert(Shopping shopping);

    int deleteByUserIdAndShoppingId(Integer userId, Integer shoppingId);

    int updateByShopping(Shopping shopping);

    Shopping selectByUserIdAndShoppingId(Integer id, Integer shoppingId);

    List<Shopping> selectAll(Integer id);

    Shopping selectByUserId(Integer shoppingId);
}