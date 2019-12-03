package com.doubles.myblog_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doubles.myblog_server.entity.TagLink;
import com.doubles.myblog_server.entity.article.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * TagLinkMapper.xml
 *
 * @author swh
 * @date 2019-10-15
 */
@Mapper
public interface TagLinkMapper extends BaseMapper<TagLink> {

}
