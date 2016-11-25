package com.oneclouder.pidm.redis.service;

/**
 * Created by clouder on 16-10-14.
 */
public interface IRedisService {

    public String set(String key,String value);

    public String get(String key);

    public Long delete(String key);

    public Long expire(String key,Integer seconds);

    public String set(String key,String value,Integer seconds);
}
