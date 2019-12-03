package com.doubles.myblog_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doubles.myblog_server.common.dto.Token;
import com.doubles.myblog_server.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserService
 *
 * @author swh
 * @date 2019-10-15
 */

public interface UserService extends IService<User> {
    /**
     * 登录
     *
     * @param params
     * @return
     */
    User getUserByAccount(@Param("param") String params);

    /**
     * 根据关联Id获取列表
     *
     * @param linkId
     * @return
     */
    List<User> listByLinkId(Integer linkId);

    /**
     * 添加所属作者，包含新增作者
     *
     * @param userList
     * @param linkId
     */
    void saveUserLink(List<Integer> userList, Integer linkId);



    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> listUser();

    /**
     * 查询除管理员外的所有用户
     *
     * @return
     */
    List<User> listUserNoAdmin();

    /**
     * 按ID查询用户
     *
     * @param id
     * @return
     */
    User getUserById(@Param("param") Integer id);

    /**
     * 生成令牌
     *
     * @param uid
     * @param scope
     * @param type
     * @param time
     * @return
     */
    Token generateToken(Integer uid, Integer scope, String type, String time);
}
