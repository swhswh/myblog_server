package com.doubles.myblog_server.entity;

import lombok.Data;

/**
 * UserLink
 *
 * @author swh
 * @date 2019-11-04
 */
@Data
public class UserLink {

    private Integer id;

    private Integer linkId;

    private Integer userId;

    public UserLink(Integer linkId, Integer userId) {
        this.linkId =  linkId;
        this.userId = userId;
    }
}
