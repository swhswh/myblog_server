package com.doubles.myblog_server.entity;

import lombok.Data;

/**
 * Link
 *
 * @author swh
 * @date 2019-11-06
 */
@Data
public class Link {

    private Integer id;

    private String name;

    private String link;

    private String avatar;
}
