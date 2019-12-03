package com.doubles.myblog_server.common.dto;

import lombok.Data;

/**
 * Token
 *
 * @author swh
 * @date 2019-10-28
 */
@Data
public class Token {

    private Integer uid;

    private Integer scope;

    private String type;

    private String secretKey;

    private String time;
}
