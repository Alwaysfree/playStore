package com.player.dao;

import com.player.pojo.Order;

import java.util.List;

public interface OrderMapper{

    int insert(Order order);

    Order selectByUserIdOrderNo(Integer id, Long orderNo);

    int updateByPrimaryKeySelective(Order updateOrder);

    Order selectByUserIdAndOrderNo(Integer userId, Long orderNo);

    List<Order> selectByUserId(Integer userId);

    Order selectByOrderNo(Long orderNo);

    List<Order> selectAllOrder();
}