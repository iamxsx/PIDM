package com.oneclouder.pidm.redis;

/**
 * Created by clouder on 16-10-14.
 */
public interface RedisCallback<T,E> {

    public T callback(E e);


}
