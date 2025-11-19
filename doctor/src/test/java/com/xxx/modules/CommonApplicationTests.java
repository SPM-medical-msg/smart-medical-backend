package com.xxx.modules;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class CommonApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        Long te = stringRedisTemplate.getExpire("test", TimeUnit.SECONDS);
        System.out.println(te);
    }
    @Test
    void contextLoads2() {
        stringRedisTemplate.opsForValue().set("test","paper start", 60, TimeUnit.SECONDS);
    }
}
