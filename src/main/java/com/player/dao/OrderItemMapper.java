package com.player.dao;

import com.player.pojo.OrderItem;

import java.util.List;

public interface OrderItemMapper{

    void batchInsert(List<OrderItem> orderItemList);

    List<OrderItem> getByOrderNoUserId(Long orderNo, Integer userId);

    List<OrderItem> getByOrderNo(Long orderNo);
}