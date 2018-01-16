package com.smallAttr.github.service;

import com.smallAttr.github.domain.User;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 下午3:10
 * @Description:
 */
public interface AuthService {

    /**
     * 注册
     * @param userToAdd
     * @return
     */
    User register(User userToAdd);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 刷新token
     * @param token
     * @return
     */
    String refresh(String token);
}
