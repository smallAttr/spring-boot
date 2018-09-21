package com.smallAttr.github.security;

import com.smallAttr.github.jwt.JwtTokenUtils;
import com.smallAttr.github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chenGang
 * @Date: 2018/1/18 上午10:41
 * @Description:
 */
@Component
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        if (authentication != null) {
            final String token = jwtTokenUtils.generateToken(authentication);
            httpServletResponse.setHeader("jwt_token", token);
        }
    }
}
