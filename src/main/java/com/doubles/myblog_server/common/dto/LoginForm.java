package com.doubles.myblog_server.common.dto;

import lombok.Data;

/**
 * LoginForm
 *
 * @author swh
 * @date 2019-10-30
 */
@Data
public class LoginForm {

    private String account;

    private String password;
}
