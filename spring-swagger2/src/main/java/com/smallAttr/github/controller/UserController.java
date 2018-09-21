package com.smallAttr.github.controller;

import com.smallAttr.github.domain.User;
import com.smallAttr.github.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: chenGang
 * Date: 2018/9/21 下午3:52
 */
@RestController
@RequestMapping("/user")
@Api("UserController相关接口")
public class UserController {


  @Autowired
  private UserService userService;


  @ApiOperation(value = "根据userId查询用户信息", notes = "根据userId查询用户信息")
  @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long")
  @GetMapping("/{id:\\d+}")
  public User findById(@PathVariable("id") Long id) {
    return userService.findById(id);
  }


  @ApiOperation(value = "根据userId查询用户信息", notes = "根据userId查询用户信息")
  @ApiImplicitParam(name = "id", value = "用户ID", paramType = "query", required = true, dataType = "Long")
  @GetMapping("")
  public User getUser(@RequestParam(name = "id") Long id) {
    return userService.findById(id);
  }



}
