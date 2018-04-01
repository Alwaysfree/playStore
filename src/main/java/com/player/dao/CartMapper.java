package com.player.dao;


import com.player.common.ServerResponse;
import com.player.pojo.Cart;
import com.player.vo.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    int insert(Cart cart);

    int update(Cart cart);

    int deleteByUserIdProductIds(@Param("userId") Integer id, @Param("productIdList") List<String> productList);

    int checkedOrUncheckedProduct(@Param("userId") Integer id, @Param("productId") Integer productId, @Param("checked") Integer checked);

    List<Cart> selectCheckedCartByUserId(Integer userId);

    int selectCartProductCount(Integer id);

    List<Cart> selectCartByUserId(Integer userId);

    int updateSelective(Cart cart);

    int selectCartProductCheckedStatusByUserId(Integer userId);

    int deleteById(Integer id);
}