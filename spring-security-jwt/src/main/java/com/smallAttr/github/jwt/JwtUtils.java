package com.smallAttr.github.jwt;

import com.smallAttr.github.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 上午11:02
 * @Description:
 */
public final class JwtUtils {

    private JwtUtils() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getLastPasswordResetDate(),
                mapToGrantedAuthorities(user.getRoles().stream().map(role -> role.name()).collect(Collectors.toList()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
