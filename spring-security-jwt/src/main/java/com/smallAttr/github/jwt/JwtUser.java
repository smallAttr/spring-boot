package com.smallAttr.github.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 上午10:56
 * @Description:
 */
@Data
public class JwtUser extends User {

    private Long id;

    private String email;

    private Date lastPasswordResetDate;

    public JwtUser(Long id, String username, String password, String email, Date lastPasswordResetDate, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
