package com.player.dao;


import com.player.common.ServerResponse;
import com.player.pojo.Cart;
import com.player.vo.CartVo;

import java.util.List;

public interface CartMapper {
    Cart selectCartByUserIdProductId(Integer userId, Integer productId);

    void insert(Cart cartItem);

    void update(Cart cart);

    ServerResponse<CartVo> deleteByUserIdProductIds(Integer id, String productIds);

    void checkedOrUncheckedProduct(Integer id, Integer productId, Integer checked);

    List<Cart> selectCheckedCartByUserId(Integer userId);

    int selectCartProductCount(Integer id);

    List<Cart> selectCartByUserId(Integer userId);

    void updateSelective(Cart cartForQuantity);

    int selectCartProductCheckedStatusByUserId(Integer userId);

    void deleteById(Integer id);
}