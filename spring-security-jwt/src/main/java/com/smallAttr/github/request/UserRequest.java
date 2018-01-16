package com.smallAttr.github.request;

import lombok.Data;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 下午6:03
 * @Description:
 */
@Data
public class UserRequest {

    private String username;

    private String password;

    private String email;

    private Long roleType;
}
