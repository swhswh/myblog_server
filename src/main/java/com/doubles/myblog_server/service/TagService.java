package com.doubles.myblog_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doubles.myblog_server.entity.tag.Tag;
import com.doubles.myblog_server.entity.tag.TagVO;
import com.doubles.myblog_server.common.util.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * TagService
 *
 * @author swh
 * @date 2019-10-23
 */

public interface TagService extends IService<Tag> {
    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    PageUtil queryPage(Map<String, Object> params);

    /**
     * 根据关联Id获取列表
     *
     * @param linkId
     * @param type
     * @return
     */
    List<Tag> listByLinkId(Integer linkId, Integer type);

    /**
     * 添加所属标签，包含新增标签
     *
     * @param tagList
     * @param linkId
     * @param type
     */
    void saveTagLink(List<Integer> tagList, Integer linkId, Integer type);

    /**
     * 删除tagLink关联
     * @param linkId
     * @param type
     */
    void deleteTagLink(Integer linkId, Integer type);

    /**
     * 获取tagVoList
     * @return
     */
    List<TagVO> listTagVo();

}
