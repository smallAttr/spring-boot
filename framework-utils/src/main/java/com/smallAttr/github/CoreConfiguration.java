package com.smallAttr.github;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @Author: chenGang
 * @Date: 2018/7/3 下午2:20
 * @Description:
 */
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.smallAttr.github")
public class CoreConfiguration {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.port}")
    private String redisPort;


    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool;
        if(StringUtils.isNotBlank(redisPassword)){
            jedisPool = new JedisPool(jedisPoolConfig, redisHost, Integer.parseInt(redisPort), 1000, null);
        }else {
            jedisPool = new JedisPool(jedisPoolConfig, redisHost, Integer.parseInt(redisPort), 1000, redisPassword);
        }
        return jedisPool;
    }
}
