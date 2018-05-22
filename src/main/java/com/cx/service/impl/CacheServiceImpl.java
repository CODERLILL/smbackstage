package com.cx.service.impl;


import com.cx.service.CacheService;
import com.cx.utils.redis.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedisPool;

@Service
public class CacheServiceImpl implements CacheService {

    private RedisUtil redisService = new RedisUtil();

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    //设置 redis的值(不设置有效期)
    @Override
    public <T> T setRedis(String key, T value) {
        return redisService.setRedis(key, value, shardedJedisPool);
    }

    //设置 redis的值 设置 过期 时间 单位为秒
    @Override
    public <T> void setRedis(String key, T value, int time) {
        redisService.setRedis(key, value, time, shardedJedisPool);
    }

    //根据redis的key获取值
    @Override
    public <T> T getRedis(String key) {
        return redisService.getRedis(key, shardedJedisPool);
    }

    //从redis里删除
    @Override
    public long removeRedis(String key) {
        return redisService.removeRedis(key, shardedJedisPool);
    }
}
