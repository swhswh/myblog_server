package com.doubles.myblog_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doubles.myblog_server.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * LinkMapper
 *
 * @author swh
 * @date 2019-11-06
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {
}
