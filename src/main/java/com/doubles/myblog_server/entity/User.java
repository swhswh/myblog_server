package com.doubles.myblog_server.entity;

import lombok.Data;

/**
 * User
 *
 * @author swh
 * @date 2019-10-15
 */
@Data
public class User {

    private Integer id;

    private String name;

    private String account;

    private String password;

    private Integer auth;

    private String avatar;

    private String email;

    private String description;
}
