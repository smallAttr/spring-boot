package com.smallAttr.github.service.impl;

import com.smallAttr.github.domain.User;
import com.smallAttr.github.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Author: chenGang
 * Date: 2018/9/21 下午3:57
 */
@Service
public class UserServiceImpl implements UserService {
  @Override
  public User findById(Long id) {
    return this.initUser(id);
  }

  private User initUser(Long id) {
    User user = new User();
    user.setId(id);
    user.setName("smallAttr");
    user.setNickname("");
    user.setEmail("1140720248@qq.com");
    return user;
  }
}
