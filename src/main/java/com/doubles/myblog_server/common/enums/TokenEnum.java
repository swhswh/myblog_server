package com.doubles.myblog_server.common.enums;

/**
 * TokenEnum
 *
 * @author swh
 * @date 2019-10-28
 */
public enum TokenEnum {

    //md5加密（doubles）
    SECRTKEY("2155d8af2e7bb07fa61c84c2e15c72b6"),

    ACCESS("access"),

    REFRESH("refresh");

    private String value;

    TokenEnum(String value) {
        this.value = value;
    }
}
