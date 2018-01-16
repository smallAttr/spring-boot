package com.smallAttr.github.service.impl;

import com.smallAttr.github.domain.User;
import com.smallAttr.github.enums.Role;
import com.smallAttr.github.jwt.JwtTokenUtils;
import com.smallAttr.github.jwt.JwtUser;
import com.smallAttr.github.repository.jpa.UserRepository;
import com.smallAttr.github.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 下午3:12
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getUsername();
        if(userRepository.findByUsername(username)!=null) {
            return null;
        }
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(passwordEncoder.encode(rawPassword));
        userToAdd.setRoles(Arrays.asList(Role.USER));
        return userRepository.save(userToAdd);
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtils.generateToken(upToken);
        return token;

    }

    @Override
    public String refresh(String token) {
        String username = jwtTokenUtils.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtils.refreshToken(token);
        }
        return null;
    }
}
