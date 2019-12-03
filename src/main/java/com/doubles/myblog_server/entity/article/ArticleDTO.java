package com.doubles.myblog_server.entity.article;

import com.doubles.myblog_server.entity.User;
import com.doubles.myblog_server.entity.tag.Tag;
import lombok.Data;

import java.util.List;

/**
 * ArticleDTO
 *
 * @author swh
 * @date 2019-10-23
 */
@Data
public class ArticleDTO extends Article{

    private List<Integer> userList;

    private List<Integer> tagList;
}
