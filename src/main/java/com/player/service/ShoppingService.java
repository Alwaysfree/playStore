package com.player.service;

import com.player.common.ServerResponse;
import com.player.pojo.Shopping;

public interface ShoppingService{

    ServerResponse add(Integer id, Shopping shopping);

    ServerResponse delete(Integer id, Integer shoppingId);

    ServerResponse update(Integer id, Shopping shopping);

    ServerResponse select(Integer id, Integer shoppingId);

    ServerResponse selectAll(Integer id, int pageNum, int pageSize);
}