package com.smallAttr.github.repository.jpa;

import com.smallAttr.github.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: chenGang
 * @Date: 2018/1/13 上午11:12
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
}
