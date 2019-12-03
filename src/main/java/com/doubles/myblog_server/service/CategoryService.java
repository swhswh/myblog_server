package com.doubles.myblog_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doubles.myblog_server.entity.Category;

import java.util.List;
import java.util.Map;

/**
 * CategoryService
 *
 * @author swh
 * @date 2019-10-15
 */
public interface CategoryService extends IService<Category> {
    /**
     * 查询所有菜单
     * @param params
     * @return
     */
    List<Category> queryWithParentName(Map<String, Object> params);

    /**
     * 根据父级别查询子级别
     * @param id
     * @return
     */
    List<Category> queryListParentId(Integer id);

    /**
     * 根据类别Id数组查询类别数组
     * @param categoryId
     * @param categoryList
     * @return
     */
    String renderCategoryArr(Integer categoryId, List<Category> categoryList);

    /**
     * 获取categoryList
     * @param params
     * @return
     */
    List<Category> listCategory(Map<String, Object> params);
}
