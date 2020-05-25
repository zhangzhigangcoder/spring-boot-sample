package org.spring.boot.cache.impl;

import org.spring.boot.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public <T> void setModel(String key, T model) {
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(model));
    }

    @Override
    public <T> void setModel(String key, T model, int seconds) {
        stringRedisTemplate.opsForValue().set(key,JSON.toJSONString(model), seconds);
    }

    @Override
    public <T> T getModel(String key, Class<T> clazz) {
        String data = stringRedisTemplate.opsForValue().get(key);
        if (null == data) {
            return null;
        }
        return JSON.parseObject(data, clazz);
    }

    @Override
    public <T> void delModel(String key) {
        stringRedisTemplate.delete(key);
    }
}
