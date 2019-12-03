package com.doubles.myblog_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doubles.myblog_server.entity.Link;

import java.util.List;

/**
 * LinkService
 *
 * @author swh
 * @date 2019-11-06
 */
public interface LinkService extends IService<Link> {
    /**
     * 友链列表
     *
     * @return
     */
    List<Link> listLink();
}
