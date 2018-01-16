package com.smallAttr.github.controller;

import com.smallAttr.github.domain.User;
import com.smallAttr.github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 上午11:30
 * @Description:
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
