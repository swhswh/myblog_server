package com.doubles.myblog_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doubles.myblog_server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserMapper.xml
 *
 * @author swh
 * @date 2019-10-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**按用户账号查询用户
     *
     * @param params
     * @return
    */
    User getUserByAccount(@Param("params") String params);

    /**
     * 根据linkId获取User列表
     * @param linkId
     * @return
     */
    List<User> listByLinkId(@Param("linkId") Integer linkId);

}
