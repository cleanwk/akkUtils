package com.akk.cache;

import com.akk.cache.temp.Func0;

import java.io.Serializable;

public interface Cache <K,V> extends Iterable<V>, Serializable {
    int capacity();

    long timeout();

    void put(K key,V object);

    void put(K key,V obgect,long timeout);

    default V get(K key){
        return get(key,true);
    }

    default V get(K key, Func0<V> supplier){
        return get
    }
}
