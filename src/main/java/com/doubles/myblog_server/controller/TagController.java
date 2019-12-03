package com.doubles.myblog_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doubles.myblog_server.common.Result;
import com.doubles.myblog_server.common.enums.ModuleEnum;
import com.doubles.myblog_server.common.util.PageUtil;
import com.doubles.myblog_server.common.validator.ValidatorUtil;
import com.doubles.myblog_server.entity.TagLink;
import com.doubles.myblog_server.entity.tag.Tag;
import com.doubles.myblog_server.entity.tag.TagVO;
import com.doubles.myblog_server.mapper.TagLinkMapper;
import com.doubles.myblog_server.service.TagService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * TagController
 *
 * @author swh
 * @date 2019-10-24
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @Resource
    private TagLinkMapper tagLinkMapper;

    /**
     * 列表
     */
    @GetMapping("/admin/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtil page = tagService.queryPage(params);

        return Result.ok().put("page", page);
    }

    @GetMapping("/admin/select")
    public Result select(@RequestParam("typeId") Integer typeId){
        List<Tag> tagList = tagService.list(new QueryWrapper<Tag>().lambda().eq(typeId != null,Tag::getTypeId,typeId));
        return Result.ok().put("tagList",tagList);
    }


    /**
     * 信息
     */
    @GetMapping("/admin/info/{id}")
    public Result info(@PathVariable("id") String id){
        Tag tag = tagService.getById(id);

        return Result.ok().put("tag", tag);
    }

    /**
     * 保存
     */
    @PostMapping("/admin/save")
    @CacheEvict(allEntries = true)
    public Result save(@RequestBody Tag tag){
        ValidatorUtil.validateEntity(tag);
        tagService.save(tag);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/admin/update")
    @CacheEvict(allEntries = true)
    public Result update(@RequestBody Tag tag){
        ValidatorUtil.validateEntity(tag);
        tagService.updateById(tag);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/admin/delete")
    @CacheEvict(allEntries = true)
    public Result delete(@RequestBody String[] ids){
        for (String id : ids) {
            List<TagLink> tagLinkList = tagLinkMapper.selectList(new QueryWrapper<TagLink>().lambda().eq(TagLink::getTagId, id));
            if(!CollectionUtils.isEmpty(tagLinkList)) {
                TagLink tagLink = tagLinkList.get(0);
                if (tagLink.getTypeId().equals(ModuleEnum.ARTICLE.getValue())) {
                    return Result.error("该标签下有文章，无法删除");
                }
                if (tagLink.getTypeId().equals(ModuleEnum.BOOK.getValue())) {
                    return Result.error("该标签下有图书，无法删除");
                }
                if(tagLink.getTypeId().equals(ModuleEnum.MOVIE.getValue())) {
                    return Result.error("该标签下有笔记，无法删除");
                }
            }
        }
        tagService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

    @GetMapping("/list")
    @Cacheable
    public Result listTag() {
        List<TagVO> tagList = tagService.listTagVo();
        return Result.ok().put("tagList",tagList);
    }
}
