package com.player.dao;

import com.player.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper{

    int insert(Order order);

    Order selectByUserIdOrderNo(@Param("userId")Integer id, @Param("orderNo")Long orderNo);

    int updateByPrimaryKeySelective(Order updateOrder);

    Order selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    List<Order> selectByUserId(Integer userId);

    Order selectByOrderNo(Long orderNo);

    List<Order> selectAllOrder();
}