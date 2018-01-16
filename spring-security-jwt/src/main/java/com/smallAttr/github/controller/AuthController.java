package com.smallAttr.github.controller;

import com.smallAttr.github.domain.User;
import com.smallAttr.github.enums.Role;
import com.smallAttr.github.request.JwtAuthenticationRequest;
import com.smallAttr.github.request.UserRequest;
import com.smallAttr.github.response.JwtAuthenticationResponse;
import com.smallAttr.github.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;


/**
 * @Author: chenGang
 * @Date: 2018/1/13 下午3:23
 * @Description:
 */
@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody UserRequest userRequest) throws AuthenticationException {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setLastPasswordResetDate(new Date());

        if (userRequest.getRoleType() == 0) {
            user.setRoles(Arrays.asList(Role.ADMIN));
        } else {
            user.setRoles(Arrays.asList(Role.USER));
        }
        return authService.register(user);
    }
}
