package com.doubles.myblog_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doubles.myblog_server.common.util.PageUtil;
import com.doubles.myblog_server.common.util.Query;
import com.doubles.myblog_server.entity.TagLink;
import com.doubles.myblog_server.entity.tag.Tag;
import com.doubles.myblog_server.entity.tag.TagVO;
import com.doubles.myblog_server.mapper.TagLinkMapper;
import com.doubles.myblog_server.mapper.TagMapper;
import com.doubles.myblog_server.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * TagServiceImpl
 *
 * @author swh
 * @date 2019-10-23
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagLinkMapper tagLinkMapper;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        IPage<Tag> page = baseMapper.selectPage(new Query<Tag>(params).getPage(),
                new QueryWrapper<Tag>().lambda());
        return new PageUtil(page);
    }

    /**
     * 根据关联Id获取列表
     *
     * @param linkId
     * @return
     */
    @Override
    public List<Tag> listByLinkId(Integer linkId, Integer typeId) {
        
        return this.baseMapper.listByLinkId(linkId, typeId);
    }

    /**
     * 添加所属标签
     *
     * @param tagList
     * @param linkId
     */
    @Override
    public void saveTagLink(List<Integer> tagList, Integer linkId, Integer typeId) {
        Optional.ofNullable(tagList)
                .ifPresent(tagListValue -> tagListValue.forEach(tagId -> {
                    TagLink tagLink = new TagLink(linkId, tagId, typeId);
                    tagLinkMapper.insert(tagLink);
                }));
    }

    /**
     * 删除tagLink关联
     *
     * @param linkId
     */
    @Override
    public void deleteTagLink(Integer linkId, Integer typeId) {
        tagLinkMapper.delete(new QueryWrapper<TagLink>().lambda()
                .eq(linkId != null, TagLink::getLinkId, linkId)
                .eq(typeId != null, TagLink::getTypeId, typeId));
    }

    /**
     * 获取tagVoList
     *
     * @return
     */
    @Override
    public List<TagVO> listTagVo() {
        return baseMapper.listTagVo();
    }
}
