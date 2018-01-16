package com.smallAttr.github.request;

import lombok.Data;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 下午3:27
 * @Description:
 */
@Data
public class JwtAuthenticationRequest {

    private String username;

    private String password;
}
