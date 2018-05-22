package com.cx.service;

/**
 * @version V1.0
 * @Title: CacheService.java <br>
 * @Package com.cx.service <br>
 * @Description: (用一句话描述该类做什么) <br>
 * @author: 路逸冰(Allen) <br>
 * @date: 2018/5/18 09:53 <br>
 */
public interface CacheService {

    //设置 redis的值(不设置有效期)
    <T> T setRedis(String key, T value);

    //设置 redis的值 设置 过期 时间 单位为秒
    <T> void setRedis(String key, T value, int time);

    //获取redis的key
    <T> T getRedis(String key);

    //删除redis的key
    long removeRedis(String key);
}
