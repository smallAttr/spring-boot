package com.smallAttr.github;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * Author: chenGang
 * Date: 2018/9/25 上午11:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTomcatConnectionPoolTest {

  @Autowired
  private DataSource dataSource;

  @Test
  public void getDataSourceClassName() {
    Assert.isTrue(dataSource.getClass().getName().equals("org.apache.tomcat.jdbc.pool.DataSource"));
  }
}
