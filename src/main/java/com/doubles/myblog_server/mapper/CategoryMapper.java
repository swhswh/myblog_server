package com.doubles.myblog_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doubles.myblog_server.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * CategoryMapper
 *
 * @author swh
 * @date 2019-10-15
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查询所有分类
     * @param params
     * @return
     */
    List<Category> listCategory(Map<String, Object> params);
}
