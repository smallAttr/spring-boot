package com.smallAttr.github.response;

import lombok.Data;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 下午3:28
 * @Description:
 */
@Data
public class JwtAuthenticationResponse {

    private String token;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
