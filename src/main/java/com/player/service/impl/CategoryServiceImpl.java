package  com.player.service.impl;

import com.player.common.ServerResponse;
import com.player.pojo.Category;
import com.player.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{

    @Override
    public ServerResponse addCategory(String name, Integer parentId) {
        return null;
    }

    @Override
    public ServerResponse updateCategory(Integer categoryId, String categoryName) {
        return null;
    }

    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        return null;
    }

    @Override
    public ServerResponse<List<Category>> selectChildrenAndCategoryById(Integer categoryId) {
        return null;
    }
}