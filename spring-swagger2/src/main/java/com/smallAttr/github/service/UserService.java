package com.smallAttr.github.service;

import com.smallAttr.github.domain.User;

/**
 * Author: chenGang
 * Date: 2018/9/21 下午3:57
 */
public interface UserService {

  User findById(Long id);
}
