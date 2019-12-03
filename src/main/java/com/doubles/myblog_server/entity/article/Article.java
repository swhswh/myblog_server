package com.doubles.myblog_server.entity.article;

import lombok.Data;

import java.util.Date;

/**
 * article
 *
 * @author swh
 * @date 2019-10-15
 */
@Data
public class Article {

    private Integer id;

    private String title;

    private String description;

    private String content;

    private String contentHtml;

    private String cover;

    private Long hitNum;

    private Long commentNum;

    private Date createTime;

    private Date updateTime;

    private String publish;

    private Boolean remove;

    private String top;

    private Integer categoryId;

    private Integer typeId;
}
