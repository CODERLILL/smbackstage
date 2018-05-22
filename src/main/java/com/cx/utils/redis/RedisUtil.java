package com.cx.utils.redis;


import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


public class RedisUtil {

    private BytesSerialize bytesSerialize = new BytesSerialize();

    /**
     * 设置 redis的值(不设置有效期)
     *
     * @param key
     * @param value
     */
    public <T> T setRedis(String key, T value,ShardedJedisPool shardedJedisPool) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.set( key.getBytes(), (byte[]) bytesSerialize.serialize(value));
            return getRedis(key,shardedJedisPool);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 设置 redis的值 设置 过期 时间 单位为秒
     *
     * @param key
     * @param value
     * @param time
     *            有效时间 为秒
     */
    public <T> void setRedis(String key, T value, int time,ShardedJedisPool shardedJedisPool) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.set( key.getBytes(), (byte[]) bytesSerialize.serialize(value));
            jedis.expire( key.getBytes(), time); // 设置 过期 时间 单位为秒
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //根据redis的key获取值
    public <T> T getRedis(String key,ShardedJedisPool shardedJedisPool) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            byte[] b = key.getBytes();
            T t = bytesSerialize.unSerialize(jedis.get(b));
            return t;
        } catch (Exception e) {
            // 如果redis报错 也不要影响后面的流程
            e.printStackTrace();
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 从redis里删除
     *
     * @param key
     */
    public long removeRedis(String key,ShardedJedisPool shardedJedisPool) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            // 如果redis报错 也不要影响后面的流程
            e.printStackTrace();
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
