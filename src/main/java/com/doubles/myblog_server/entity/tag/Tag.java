package com.doubles.myblog_server.entity.tag;

import lombok.Data;

/**
 * Tag
 *
 * @author swh
 * @date 2019-10-15
 */
@Data
public class Tag {

    private Integer id;

    private String name;

    private Integer typeId;
}
