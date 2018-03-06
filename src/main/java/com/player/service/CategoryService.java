package com.player.service;

import com.player.common.ServerResponse;
import com.player.pojo.Category;

import java.util.List;

public interface CategoryService{
    ServerResponse addCategory(String name,Integer parentId);
    ServerResponse updateCategory(Integer categoryId,String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectChildrenAndCategoryById(Integer categoryId);
}