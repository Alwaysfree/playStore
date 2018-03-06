package  com.player.dao;


import com.player.pojo.Category;

import java.util.List;

public interface CategoryMapper{
    int addCategory(Category category);
    int deleteById(Integer categoryId);
    int updateCategory(Category category);
    List<Category> selectCategoryChildrenByParentId(Integer parentId);
    Category selectById(Integer category);
}