package com.player.dao;

import com.player.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper{

    void batchInsert(@Param("orderItemList")List<OrderItem> orderItemList);

    List<OrderItem> getByOrderNoUserId(@Param("orderNo") Long orderNo, @Param("userId") Integer userId);

    List<OrderItem> getByOrderNo(Long orderNo);
}