package com.smallAttr.github.service;

import com.smallAttr.github.domain.User;
import com.smallAttr.github.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 上午11:31
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsers() {
        return userRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
