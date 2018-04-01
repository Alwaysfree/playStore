package com.player.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.player.common.ServerResponse;
import com.player.dao.ShoppingMapper;
import com.player.pojo.Shopping;
import com.player.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService{

    @Autowired
    private ShoppingMapper shoppingMapper;


    /**
     * 新建地址
     * @param userId
     * @param shopping
     * @return
     */
    @Override
    public ServerResponse add(Integer userId, Shopping shopping) {
        shopping.setUserId(userId);
        int rowCount = shoppingMapper.insert(shopping);
        if (rowCount>0){
            Map map = Maps.newHashMap();
            map.put("shoppingId",shopping.getId());
            return ServerResponse.createBySuccess("新建地址成功",map);
        }
        return ServerResponse.createBySuccess("新建地址失败");
    }

    /**
     * 删除地址
     * @param userId
     * @param shoppingId
     * @return
     */
    @Override
    public ServerResponse<String> delete(Integer userId, Integer shoppingId) {
        int rowCount = shoppingMapper.deleteByUserIdAndShoppingId(userId,shoppingId);
        if (rowCount>0){
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createBySuccess("删除地址失败");
    }

    /**
     * 更新地址
     * @param id
     * @param shopping
     * @return
     */
    @Override
    public ServerResponse update(Integer id, Shopping shopping) {
        shopping.setUserId(id);
        int rowCount = shoppingMapper.updateByShopping(shopping);
        if (rowCount>0){
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    /**
     *根据用户id,地址id查询地址
     * @param id
     * @param shoppingId
     * @return
     */
    @Override
    public ServerResponse<Shopping> select(Integer id, Integer shoppingId) {
        Shopping shopping = shoppingMapper.selectByUserIdAndShoppingId(id,shoppingId);
        if (shopping==null){
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccess("成功查询到该地址",shopping);
    }


    /**
     *查询地址列表
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse selectAll(Integer id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Shopping> lists = shoppingMapper.selectByUserId(id);
        PageInfo pageInfo = new PageInfo(lists);
        return ServerResponse.createBySuccess(pageInfo);
    }
}