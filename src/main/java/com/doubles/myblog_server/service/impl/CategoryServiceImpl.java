package com.doubles.myblog_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doubles.myblog_server.entity.Category;
import com.doubles.myblog_server.mapper.CategoryMapper;
import com.doubles.myblog_server.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CategoryServiceImpl
 *
 * @author swh
 * @date 2019-10-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    /**
     * 查询所有菜单
     *
     * @param params
     * @return
     */
    @Override
    public List<Category> queryWithParentName(Map<String, Object> params) {

        return baseMapper.listCategory(params);
    }

    /**
     * 根据父级别查询子级别
     *
     * @param id
     * @return
     */
    @Override
    public List<Category> queryListParentId(Integer id) {
        return baseMapper.selectList(new QueryWrapper<Category>().lambda()
                .eq(Category::getTypeId,id));
    }

    /**
     * 根据类别Id数组查询类别数组
     * @param categoryId
     * @param categoryList
     * @return
     */
    @Override
    public String renderCategoryArr(Integer categoryId, List<Category> categoryList) {
        if (StringUtils.isEmpty(categoryId)) {
            return "";
        }
        String categoryName;
        // 根据Id查找类别名称
        String categoryStr = categoryList
                .stream()
                .filter(category -> category.getId().equals(categoryId))
                .map(Category::getName)
                .findAny().orElse("类别已被删除");
        categoryName = categoryStr;
        return String.join(",",categoryName);
    }

    /**
     * 获取categoryList
     *
     * @param params
     * @return
     */
    @Override
    public List<Category> listCategory(Map<String, Object> params) {
//        String type = (String) params.get("type");
//        String rank = (String) params.get("rank");
//        return baseMapper.selectList(new QueryWrapper<Category>().lambda()
//                .eq(org.apache.commons.lang.StringUtils.isNotEmpty(type),Category::getTypeId,type)
//                .eq(org.apache.commons.lang.StringUtils.isNotEmpty(rank),Category::getRank,rank));
        return null;
    }
}
