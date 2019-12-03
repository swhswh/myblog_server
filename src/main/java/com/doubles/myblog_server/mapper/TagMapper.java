package com.doubles.myblog_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doubles.myblog_server.entity.article.Article;
import com.doubles.myblog_server.entity.tag.Tag;
import com.doubles.myblog_server.entity.tag.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TagMapper
 *
 * @author swh
 * @date 2019-10-15
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据linkId获取Tag列表
     * @param linkId
     * @param typeId
     * @return
     */
    List<Tag> listByLinkId(@Param("linkId") Integer linkId, @Param("typeId") Integer typeId);

    /**
     * 根据linkId删除多对多关联
     * @param linkId
     * @param typeId
     */
    void deleteTagLink(@Param("linkId") Integer linkId, @Param("typeId") Integer typeId);


    /**
     * 获取tagVoList
     * @return
     */
    List<TagVO> listTagVo();
}
