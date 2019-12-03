package com.doubles.myblog_server.controller;

import com.doubles.myblog_server.common.Result;
import com.doubles.myblog_server.common.util.PageUtil;
import com.doubles.myblog_server.common.validator.ValidatorUtil;
import com.doubles.myblog_server.entity.article.Article;
import com.doubles.myblog_server.entity.article.ArticleDTO;
import com.doubles.myblog_server.entity.article.ArticleVO;
import com.doubles.myblog_server.service.ArticleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ArticleController
 *
 * @author swh
 * @date 2019-10-23
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/admin/list")
    public Result listArticle(@RequestParam Map<String, Object> params) {
        PageUtil page = articleService.queryPage(params);
        return Result.ok().put("articleList",page);
    }

    @GetMapping("/admin/info/{articleId}")
    public Result info(@PathVariable Integer articleId) {
        ArticleDTO article = articleService.getArticle(articleId);
        return Result.ok().put("article",article);
    }

    @PostMapping("/admin/save")
    @CacheEvict(allEntries = true)
    public Result saveArticle(@RequestBody ArticleDTO article){
        ValidatorUtil.validateEntity(article);
        articleService.saveArticle(article);
        return Result.ok();
    }

    @PutMapping("/admin/update")
    @CacheEvict(allEntries = true)
    public Result updateArticle(@RequestBody ArticleDTO article){
        ValidatorUtil.validateEntity(article);
        article.setUpdateTime(new Date());
        articleService.updateArticle(article);
        return Result.ok();
    }

    @PutMapping("/admin/update/status")
    @CacheEvict(allEntries = true)
    public Result updateStatus(@RequestBody Article article) {
        articleService.updateById(article);
        return Result.ok();
    }


    @PutMapping("/admin/delete")
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public Result deleteBatch(@RequestParam Integer articleId) {
        articleService.deleteBatch(articleId);
        return Result.ok();
    }

    @GetMapping("/{articleId}")
    public Result getArticle(@PathVariable Integer articleId){
        ArticleVO article = articleService.getArticleVo(articleId);
        return Result.ok().put("article",article);
    }

    @GetMapping("/list")
    @Cacheable
    public Result list(@RequestParam Map<String, Object> params){
        PageUtil page = articleService.queryPageCondition(params);
        return Result.ok().put("page",page);
    }

    @GetMapping("/top")
    @Cacheable
    public Result topArticles(){
        List<ArticleVO> articleList = articleService.getTopArticleList();
        return Result.ok().put("topArticles",articleList);
    }

    @GetMapping("/archive")
    @Cacheable
    public Result archiveArticles(){
        List<ArticleVO> articleList = articleService.getArchiveArticleList();
        return Result.ok().put("archiveArticles",articleList);
    }
}
