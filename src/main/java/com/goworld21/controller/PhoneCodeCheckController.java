package com.goworld21.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手机验证码校验
 * Created by DELL on 2017/11/3.
 */
@RestController
public class PhoneCodeCheckController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/verifyCheck")
    public boolean getCode(@RequestParam("phone") String phone, @RequestParam("type") int type, @RequestParam("verify") String verify) {
        String key = phone + "_" + type;
        //查看key是否存在
        Boolean key2 = stringRedisTemplate.hasKey(key);
        //如果存在则对与验证码比对
        if (key2) {
            //获取redis中的value值（验证码）
            String key3 = stringRedisTemplate.opsForValue().get(key);
            if (verify.equals(key3)) {
                return true;
            }
        }
        
        return false;
    }
}
