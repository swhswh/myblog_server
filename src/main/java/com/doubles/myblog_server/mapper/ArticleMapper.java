package com.doubles.myblog_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doubles.myblog_server.entity.Category;
import com.doubles.myblog_server.entity.article.Article;
import com.doubles.myblog_server.entity.article.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ArticleMapper.xml
 *
 * @author swh
 * @date 2019-10-15
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 查询置顶文章列表
     *
     * @return
     */
    List<ArticleVO> listTopArticleVo();

    /**
     * 查询归档文章列表
     *
     * @return
     */
    List<ArticleVO> listArchiveArticleVo();
    /**
     * 按条件分页查询文章列表
     *
     * @param page
     * @param params
     * @return
     */
    List<ArticleVO> listArticleVOByQuery(Page<ArticleVO> page, @Param("params") Map<String, Object> params);

    /**
     * 按ID查询文章
     * @param articleId
     * @return
     */
    ArticleVO getArticleVOById(Integer articleId);

    /**
     * 批量删除文章
     * @param articleId
     */
    void deleteBatch(Integer articleId);
    /**
     * 更新点击数
     * @param articleId
     */
    void updateHitNum(Integer articleId);


    /**
     * 更新评论数
     * @param articleId
     */
    void updateCommentNum(Integer articleId);

    /**
     * 判断类别下是否有文章
     * @param categoryId
     * @return
     */
    int checkByCategory(Integer categoryId);
}
