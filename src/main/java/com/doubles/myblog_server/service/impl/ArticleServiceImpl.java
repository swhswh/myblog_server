package com.doubles.myblog_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doubles.myblog_server.common.enums.ModuleEnum;
import com.doubles.myblog_server.common.util.PageUtil;
import com.doubles.myblog_server.common.util.Query;
import com.doubles.myblog_server.entity.Category;
import com.doubles.myblog_server.entity.article.Article;
import com.doubles.myblog_server.entity.article.ArticleDTO;
import com.doubles.myblog_server.entity.article.ArticleVO;
import com.doubles.myblog_server.mapper.ArticleMapper;
import com.doubles.myblog_server.service.ArticleService;
import com.doubles.myblog_server.service.CategoryService;
import com.doubles.myblog_server.service.TagService;
import com.doubles.myblog_server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ArticleServiceImpl
 *
 * @author swh
 * @date 2019-10-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    /**
     * 分页查询博文列表（admin）
     *
     * @param params
     * @return
     */
    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        Page<ArticleVO> page = new Query<ArticleVO>(params).getPage();
        List<ArticleVO> articleList = baseMapper.listArticleVOByQuery(page,params);
        //查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getTypeId, ModuleEnum.ARTICLE.getValue()));
        //封装ArticleVO
        Optional.ofNullable(articleList).ifPresent((articleVos ->
                articleVos.forEach(articleVo -> {
                    // 设置类别
                    articleVo.setCategoryName(categoryService.renderCategoryArr(articleVo.getCategoryId(),categoryList));
                    // 设置作者列表
                    articleVo.setUserList(userService.listByLinkId(articleVo.getId()));
                    // 设置标签列表
                    articleVo.setTagList(tagService.listByLinkId(articleVo.getId(),ModuleEnum.ARTICLE.getValue()));
                })));
        page.setRecords(articleList);
        return new PageUtil(page);
    }

    /**
     * 保存博文文章（admin）
     *
     * @param article
     */
    @Override
    public void saveArticle(ArticleDTO article) {
        baseMapper.insert(article);
        tagService.saveTagLink(article.getTagList(),article.getId(),ModuleEnum.ARTICLE.getValue());
        userService.saveUserLink(article.getUserList(),article.getId());
    }

    /**
     * 批量删除（admin）
     *
     * @param articleId
     */
    @Override
    public void deleteBatch(Integer articleId) {
        this.baseMapper.deleteBatch(articleId);
    }

    /**
     * 更新博文（admin）
     *
     * @param article
     */
    @Override
    public void updateArticle(ArticleDTO article) {
        // 删除多对多所属标签
        tagService.deleteTagLink(article.getId(),ModuleEnum.ARTICLE.getValue());
        // 更新所属标签
        tagService.saveTagLink(article.getTagList(),article.getId(), ModuleEnum.ARTICLE.getValue());
        // 更新博文
        baseMapper.updateById(article);
    }

    /**
     * 获取文章对象（admin）
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleDTO getArticle(Integer articleId) {
        ArticleDTO articleDto = new ArticleDTO();
        Article article = this.baseMapper.selectById(articleId);
        BeanUtils.copyProperties(article,articleDto);
        // 查询所属标签
        //articleDto.setTagList(tagService.listByLinkId(articleId,ModuleEnum.ARTICLE.getValue()));
        return articleDto;
    }

    @Override
    public boolean checkByCategory(Integer categoryId) {
        return baseMapper.checkByCategory(categoryId) > 0;
    }

    /**
     * 分页查询博文列表（portal）
     *
     * @param params
     * @return
     */
    @Override
    public PageUtil queryPageCondition(Map<String, Object> params) {
        Page<ArticleVO> page = new Query<ArticleVO>(params).getPage();
        List<ArticleVO> articleList = baseMapper.listArticleVOByQuery(page, params);
        //查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getTypeId, ModuleEnum.ARTICLE.getValue()));
        // 封装ArticleVo
        Optional.ofNullable(articleList).ifPresent((articleVos ->
                articleVos.forEach(articleVo -> {
                    // 设置类别
                    articleVo.setCategoryName(categoryService.renderCategoryArr(articleVo.getCategoryId(),categoryList));
                    // 设置作者列表
                    articleVo.setUserList(userService.listByLinkId(articleVo.getId()));
                })));
        page.setRecords(articleList);
        return new PageUtil(page);
    }

    /**
     * 查询置顶文章列表（portal）
     * @return
     */
    @Override
    public List<ArticleVO> getTopArticleList(){
        List<ArticleVO>articleList = baseMapper.listTopArticleVo();
        //查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getTypeId, ModuleEnum.ARTICLE.getValue()));
        Optional.ofNullable(articleList).ifPresent((articleVoS ->
                articleVoS.forEach(articleVo -> {
                    // 设置类别
                    articleVo.setCategoryName(categoryService.renderCategoryArr(articleVo.getCategoryId(),categoryList));
                    // 设置作者列表
                    articleVo.setUserList(userService.listByLinkId(articleVo.getId()));
                })));
        return articleList;
    }

    @Override
    public List<ArticleVO> getArchiveArticleList(){
        List<ArticleVO>articleList = baseMapper.listArchiveArticleVo();
        Optional.ofNullable(articleList).ifPresent((articleVoS ->
                articleVoS.forEach(articleVo -> {
                    // 设置作者列表
                    articleVo.setUserList(userService.listByLinkId(articleVo.getId()));
                })));
        return articleList;
    }

    @Override
    public ArticleVO getArticleVo(Integer articleId) {
        ArticleVO articleVo = baseMapper.getArticleVOById(articleId);
        //查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getTypeId, ModuleEnum.ARTICLE.getValue()));
        //设置类别
        articleVo.setCategoryName(categoryService.renderCategoryArr(articleVo.getCategoryId(),categoryList));
        //设置作者
        articleVo.setUserList(userService.listByLinkId(articleVo.getId()));
        return articleVo;
    }
}
