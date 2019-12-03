package com.doubles.myblog_server.common.enums;

import lombok.Getter;

/**
 * ModuleEnum
 *
 * @author swh
 * @date 2019-10-23
 */
@Getter
public enum ModuleEnum {
    /**
     * 文章模块
     */
    ARTICLE(1),
    /**
     * 图书模块
     */
    BOOK(2),
    /**
     * 图书笔记模块
     */
    MOVIE(3);

    private int value;

    ModuleEnum(int value) {
        this.value = value;
    }
}
