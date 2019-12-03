package com.doubles.myblog_server.entity;

import lombok.Data;

/**
 * TagLink
 *
 * @author swh
 * @date 2019-10-15
 */
@Data
public class TagLink {

    private Integer id;

    private Integer linkId;

    private Integer tagId;

    private Integer typeId;

    public TagLink(Integer linkId, Integer tagId,Integer typeId) {
        this.linkId =  linkId;
        this.tagId = tagId;
        this.typeId = typeId;
    }
}
