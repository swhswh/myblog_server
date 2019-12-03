package com.doubles.myblog_server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Category
 *
 * @author swh
 * @date 2019-10-15
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer typeId;

    //private String parentName;
}
