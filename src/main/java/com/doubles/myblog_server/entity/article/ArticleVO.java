package com.doubles.myblog_server.entity.article;

import com.doubles.myblog_server.entity.User;
import com.doubles.myblog_server.entity.tag.Tag;
import lombok.Data;

import java.util.List;

/**
 * ArticleVO
 *
 * @author swh
 * @date 2019-10-15
 */
@Data
public class ArticleVO extends Article{
    private List<User> userList;
    private String categoryName;
    private List<Tag> tagList;
}
