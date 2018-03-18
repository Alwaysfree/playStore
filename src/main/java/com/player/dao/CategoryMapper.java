package  com.player.dao;


import com.player.pojo.Category;

import java.util.List;

public interface CategoryMapper{

    int addCategory(Category category);

    int updateCategory(Category category);

    List<Category> selectCategoryChildrenByParentId(Integer categoryId);

    Category selectById(Integer categoryId);
}