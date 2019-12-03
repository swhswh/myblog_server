package com.doubles.myblog_server.controller;

import com.doubles.myblog_server.common.Result;
import com.doubles.myblog_server.entity.Link;
import com.doubles.myblog_server.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * LinkController
 *
 * @author swh
 * @date 2019-11-06
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    LinkService linkService;

    /**
     * 友链列表
     *
     * @return
     */
    @RequestMapping("/links")
    public Result listLink(){
        List<Link> linkList = linkService.listLink();
        return Result.ok().put("links",linkList);
    }
}
