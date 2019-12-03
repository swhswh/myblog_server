package com.doubles.myblog_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doubles.myblog_server.entity.Link;
import com.doubles.myblog_server.mapper.LinkMapper;
import com.doubles.myblog_server.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * LinkServiceImpl
 *
 * @author swh
 * @date 2019-11-06
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     * 友链列表
     *
     * @return
     */
    public List<Link> listLink(){
        return baseMapper.selectList(new QueryWrapper<Link>());
    }
}
