package  com.player.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.player.common.ServerResponse;
import com.player.dao.CategoryMapper;
import com.player.pojo.Category;
import com.player.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    /**
     *  添加品类
     * @author alwaysfree
     * @date 2018/3/6 15:54
     * @param [name, parentId]
     * @return com.player.common.ServerResponse
     */
    public ServerResponse addCategory(String name, Integer parentId) {
        if (parentId==null || StringUtils.isBlank(name)){
            return ServerResponse.createByErrorMessage("添加品类名错误");
        }
        Category category = new Category();
        category.setName(name);
        category.setParentId(parentId);
        //此分类可以用
        category.setStatus(true);

        int resultCode = categoryMapper.addCategory(category);
        if (resultCode>0){
           return  ServerResponse.createBySuccess("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    @Override
    /**
     * 更新品类名
     * @author alwaysfree
     * @date 2018/3/6 16:05
     * @param [categoryId, categoryName]
     * @return com.player.common.ServerResponse
     */
    public ServerResponse updateCategory(Integer categoryId, String categoryName) {
        if (categoryId==null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新品类名错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int resultCode = categoryMapper.updateCategory(category);
        if (resultCode>0){
            return ServerResponse.createBySuccess("更新品类名成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名失败");
    }


    @Override
    /**
     *  查找当前分类的子节点
     * @author alwaysfree
     * @date 2018/3/6 16:09
     * @param [categoryId]
     * @return com.player.common.ServerResponse<java.util.List<com.player.pojo.Category>>
     */
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categories = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (categories.isEmpty()){
            return ServerResponse.createByErrorMessage("未找到当前节点的子分类");
        }
        return ServerResponse.createBySuccess(categories);
    }

    /**
     * 查找子节点
     * @author alwaysfree
     * @date 2018/3/6 16:27
     * @param [categorySet, categoryId]
     * @return java.util.Set<com.player.pojo.Category>
     */
    private Set<Category> findChildCategory(Set<Category> categorySet ,Integer categoryId){
        Category category = categoryMapper.selectById(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for(Category categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }


    @Override
    /**
     * 递归查询本节点的id及孩子节点的id
     * @author alwaysfree
     * @date 2018/3/6 16:37
     * @param [categoryId]
     * @return com.player.common.ServerResponse<java.util.List<com.player.pojo.Category>>
     */
    public ServerResponse<List<Integer>> selectChildrenAndCategoryById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        List<Integer> categoryList = Lists.newArrayList();
        if (categoryId!=null){
            for (Category category:categorySet){
                categoryList.add(category.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryList);
    }
}