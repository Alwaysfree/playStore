package com.player.service;


import com.player.common.ServerResponse;
import com.player.vo.CartVo;

public interface CartService{
    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> add(Integer id, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer id, Integer productId, Integer count);

    ServerResponse<CartVo> delete(Integer id, String productIds);

    ServerResponse<CartVo> selectOrNot(Integer id,Integer productId,Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer id);
}