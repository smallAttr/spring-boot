package com.smallAttr.github.domain;

import com.smallAttr.github.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 上午10:48
 * @Description:
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private Date LastPasswordResetDate;

    @ElementCollection
    private List<Role> roles;
}
