package com.player.service;

import com.github.pagehelper.PageInfo;
import com.player.common.ServerResponse;
import com.player.vo.OrderVo;

public interface OrderService{

    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    ServerResponse<OrderVo> manageDetail(Long orderNo);

    ServerResponse<PageInfo> manageSearchl(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> manageSend(Long orderNo);

    ServerResponse create(Integer id, Integer shoppingId);

    ServerResponse detail(Integer id, Long orderNo);

    ServerResponse list(Integer id, int pageNum, int pageSize);

    ServerResponse cancel(Integer id, Long orderNo);

    ServerResponse getProduct(Integer id);
}