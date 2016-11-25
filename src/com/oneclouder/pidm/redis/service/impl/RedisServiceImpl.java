package com.oneclouder.pidm.redis.service.impl;

import com.oneclouder.pidm.redis.RedisCallback;
import com.oneclouder.pidm.redis.service.IRedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * Created by clouder on 16-10-14.
 */
@Service("redisService")
public class RedisServiceImpl implements IRedisService {

    @Resource
    private ShardedJedisPool shardedJedisPool;

    public <T> T execute(RedisCallback<T,ShardedJedis> function){
        ShardedJedis shardedJedis = null;
        try {
            //从连接池中获取jedis分片式对象
            shardedJedis = shardedJedisPool.getResource();
            //真正执行的逻辑在 callback 中
            return function.callback(shardedJedis);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != shardedJedis){
                //关闭,检测连接是否有效,有效则放回连接池中,无效则重置状态
                shardedJedis.close();
            }
        }
        return null;
    }

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    @Override
    public String set(String key,String value){
        return this.execute(new RedisCallback<String, ShardedJedis>() {
            @Override
            public String callback(ShardedJedis shardedJedis) {
                return shardedJedis.set(key,value);
            }
        });
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    @Override
    public String get(String key){
        return this.execute(new RedisCallback<String, ShardedJedis>() {
            @Override
            public String callback(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }

    /**
     * 删除时间
     * @param key
     * @return
     */
    @Override
    public Long delete(String key){
        return this.execute(new RedisCallback<Long, ShardedJedis>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }

    /**
     * 设置生存时间
     * @param key
     * @return
     */
    @Override
    public Long expire(String key,Integer seconds){
        return this.execute(new RedisCallback<Long, ShardedJedis>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key,seconds);
            }
        });
    }

    /**
     * 设置值并制定生存时间
     * @param key
     * @param value
     * @return
     */
    @Override
    public String set(String key,String value,Integer seconds){
        return this.execute((ShardedJedis shardedJedis) -> {
            String result = shardedJedis.set(key,value);
            shardedJedis.expire(key,seconds);
            return result;
        });
    }

}
