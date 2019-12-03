package com.doubles.myblog_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doubles.myblog_server.common.Result;
import com.doubles.myblog_server.common.validator.ValidatorUtil;
import com.doubles.myblog_server.entity.Category;
import com.doubles.myblog_server.service.ArticleService;
import com.doubles.myblog_server.service.CategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * CategoryController
 *
 * @author swh
 * @date 2019-10-24
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ArticleService articleService;

    /**
     * 列表
     */
    @RequestMapping("/admin/list")
    public Result list(@RequestParam Map<String, Object> params){
        List<Category> categoryList = categoryService.queryWithParentName(params);
        return Result.ok().put("categoryList",categoryList);
    }

    /**
     * 大类列表
     */
    @RequestMapping("/admin/select")
    public Result select(@RequestParam("typeId") Integer typeId){
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(typeId!=null,Category::getTypeId,typeId));
        return Result.ok().put("categoryList",categoryList);
    }

    /**
     * 信息
     */
    @RequestMapping("/admin/info/{id}")
    public Result info(@PathVariable("id") Integer id){
        Category category = categoryService.getById(id);

        return Result.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/admin/save")
    @CacheEvict(allEntries = true)
    public Result save(@RequestBody Category category){
        // 数据校验
        ValidatorUtil.validateEntity(category);
        //verifyCategory(category);
        categoryService.save(category);

        return Result.ok();
    }

    /**
     * 数据校验
     * @param category
     */
//    private void verifyCategory(Category category) {
//        //上级分类级别
//        int parentRank = CategoryRankEnum.ROOT.getValue();
//        if (category.getTypeId() != CategoryRankEnum.FIRST.getValue()
//                && category.getTypeId() != CategoryRankEnum.ROOT.getValue()) {
//            Category parentCategory = categoryService.getById(category.getTypeId());
//            parentRank = parentCategory.getRank();
//        }
//
//        // 一级
//        if (category.getRank() == CategoryRankEnum.FIRST.getValue()) {
//            if (category.getTypeId() != CategoryRankEnum.ROOT.getValue()){
//                throw new MyException("上级目录只能为根目录");
//            }
//        }
//
//        //二级
//        if (category.getRank() == CategoryRankEnum.SECOND.getValue()) {
//            if (parentRank != CategoryRankEnum.FIRST.getValue()) {
//                throw new MyException("上级目录只能为一级类型");
//            }
//        }
//
//        //三级
//        if (category.getRank() == CategoryRankEnum.THIRD.getValue()) {
//            if (parentRank != CategoryRankEnum.SECOND.getValue()) {
//                throw new MyException("上级目录只能为二级类型");
//            }
//        }
//    }

    /**
     * 修改
     */
    @RequestMapping("/admin/update")
    @CacheEvict(allEntries = true)
    public Result update(@RequestBody Category category){
        categoryService.updateById(category);

        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/admin/delete/{id}")
    @CacheEvict(allEntries = true)
    public Result delete(@PathVariable Integer id){

        //判断是否有子菜单或按钮
        List<Category> categoryList = categoryService.queryListParentId(id);
        if(categoryList.size() > 0){
            return Result.error("请先删除子级别");
        }
        // 判断是否有文章
        if(articleService.checkByCategory(id)) {
            return Result.error("该类别下有文章，无法删除");
        }

        categoryService.removeById(id);

        return Result.ok();
    }

    @GetMapping("/list")
    @Cacheable
    public Result listCategory(@RequestParam Map<String,Object> params) {
        List<Category> categoryList = categoryService.listCategory(params);
        return Result.ok().put("categoryList",categoryList);
    }
}
