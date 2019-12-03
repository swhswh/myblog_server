package com.doubles.myblog_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doubles.myblog_server.common.dto.Token;
import com.doubles.myblog_server.common.enums.TokenEnum;
import com.doubles.myblog_server.entity.TagLink;
import com.doubles.myblog_server.entity.User;
import com.doubles.myblog_server.entity.UserLink;
import com.doubles.myblog_server.mapper.UserLinkMapper;
import com.doubles.myblog_server.mapper.UserMapper;
import com.doubles.myblog_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author swh
 * @date 2019-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    UserLinkMapper userLinkMapper;

    @Override
    public List<User> listUser(){
        return baseMapper.selectList(new QueryWrapper<User>());
    }

    @Override
    public List<User> listUserNoAdmin(){
        return baseMapper.selectList(new QueryWrapper<User>().lambda().gt(User::getId,1));
    }
    @Override
    public User getUserByAccount(String params) {
        return baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getAccount,params));
    }

    /**
     * 根据关联Id获取列表
     *
     * @param linkId
     * @return
     */
    @Override
    public List<User> listByLinkId(Integer linkId) {
        return this.baseMapper.listByLinkId(linkId);
    }

    /**
     * 添加所属作者
     *
     * @param userList
     * @param linkId
     */
    @Override
    public void saveUserLink(List<Integer> userList, Integer linkId) {
        Optional.ofNullable(userList)
                .ifPresent(userListValue -> userListValue.forEach(userId -> {
                    UserLink userLink = new UserLink(linkId, userId);
                    userLinkMapper.insert(userLink);
                }));
    }

    @Override
    public User getUserById(Integer id){
        return baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId,id));
    }

    public Token generateToken(Integer uid, Integer scope, String type, String time) {
        Token token = new Token();
        token.setUid(uid);
        token.setScope(scope);
        token.setType(type);
        token.setSecretKey(TokenEnum.SECRTKEY.toString());
        token.setTime(time);
        return token;
    }
}
