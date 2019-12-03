package com.doubles.myblog_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doubles.myblog_server.entity.article.Article;
import com.doubles.myblog_server.entity.article.ArticleDTO;
import com.doubles.myblog_server.entity.article.ArticleVO;
import com.doubles.myblog_server.common.util.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * ArticleService
 *
 * @author swh
 * @date 2019-10-15
 */
public interface ArticleService extends IService<Article> {
    /**
     * 分页查询博文列表
     * @param params
     * @return
     */
    PageUtil queryPage(Map<String, Object> params);

    /**
     * 获取置顶文章列表
     *
     * @return
     */
    List<ArticleVO> getTopArticleList();

    /**
     * 获取归档文章列表
     *
     * @return
     */
    List<ArticleVO> getArchiveArticleList();

    /**
     * 保存博文文章
     * @param article
     */
    void saveArticle(ArticleDTO article);

    /**
     * 批量删除
     * @param articleId
     */
    void deleteBatch(Integer articleId);

    /**
     * 更新博文
     * @param article
     */
    void updateArticle(ArticleDTO article);

    /**
     * 获取文章对象
     * @param articleId
     * @return
     */
    ArticleDTO getArticle(Integer articleId);


    boolean checkByCategory(Integer categoryId);

    /**
     * 分页分类获取列表
     * @param params
     * @return
     */
    PageUtil queryPageCondition(Map<String, Object> params);

    /**
     * 获取ArticleVo对象
     * @param articleId
     * @return
     */
    ArticleVO getArticleVo(Integer articleId);
}
