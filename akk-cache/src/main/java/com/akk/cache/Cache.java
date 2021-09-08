package com.akk.cache;

import com.akk.cache.impl.CacheObj;
import com.akk.cache.temp.Func0;

import java.io.Serializable;
import java.util.Iterator;

public interface Cache<K, V> extends Iterable<V>, Serializable {
    /**
     * 接口方法分为
     *
     * 获取基本信息:
     *      容量,缓存数量,是否空,是否满,缓存的失效时长,是否包括key
     * 基本操作:
     *      清空缓存,移除指定缓存
     *
     * put:
     *      默认put(默认过期时间),提供刷新可选
     * get:
     *      根据 key 不存在返回 null
     *
     */




    /**
     * 返回缓存容量, 0 表示大小没有限制
     *
     * @return 缓存容量
     */
    int capacity();

    /**
     * 缓存失效时长
     *
     * @return 缓存失效时长, 单位毫秒
     */
    long timeout();

    /**
     * 将对象加入到缓存中,默认失效时长
     *
     * @param key    键
     * @param object 缓存对象
     */
    void put(K key, V object);

    /**
     * 将对象加入到缓存中,指定失效时长
     *
     * @param key     键
     * @param object  缓存对象
     * @param timeout 失效时长,单位为毫秒
     */
    void put(K key, V object, long timeout);

    /**
     * 从缓存获取对象,不存在或者已经过期返回 null
     * 调用时,首先检查上次调用时间,与当前使用差值大于超时时间返回 null ,否则返回值
     * 调用之后,自动刷新最后访问时间
     *
     * @param key 键
     * @return 键对应的对象
     */
    default V get(K key) {
        return get(key,true);
    }

    /**
     * 从缓存中获得对象，当对象不在缓存中或已经过期返回Func0回调产生的对象
     * 调用此方法时，会检查上次调用时间，如果与当前时间差值大于超时时间返回 null 否则返回值。
     * 每次调用此方法会刷新最后访问时间
     *
     * @param key      键
     * @param supplier 如果不存在回调方法，用于生产值对象
     * @return 值对象
     */
    default V get(K key, Func0<V> supplier) {
        return get(key,true,supplier);
    }

    /**
     * 从缓存中获取对象,不再或者已经过期返回 Func0 回调产生的对象
     * 调用此方法时，会检查上次调用时间，如果与当前时间差值大于超时时间返回{@code null}，否则返回值。
     * 每次调用此方法会可选是否刷新最后访问时间， true 表示会重新计算超时时间。
     *
     * @param key                键
     * @param isUpdateLastAccess 是否更新最后访问时间,即重新计算超时时间
     * @param supplier           如果不存在回调方法用于生产值对象
     * @return 值对象
     */
    V get(K key, boolean isUpdateLastAccess, Func0<V> supplier);

    /**
     * 从缓存中获得对象，当对象不在缓存中或已经过期返回{@code null}
     * 调用此方法时，会检查上次调用时间，如果与当前时间差值大于超时时间返回{@code null}，否则返回值。
     * 每次调用此方法会可选是否刷新最后访问时间，{@code true}表示会重新计算超时时间。
     *
     * @param key                键
     * @param isUpdateLastAccess 是否更新最后访问时间，即重新计算超时时间。
     * @return 键对应的对象
     */
    V get(K key, boolean isUpdateLastAccess);

    /**
     * 返回包含键和值的迭代器
     *
     * @return 缓存对象迭代器
     */
    Iterator<CacheObj<K,V>> cacheObjIterator();

    /**
     * 从缓存中清理过期对象,清理策略取决于具体实现
     *
     * @return 清理的缓存对象个数
     */
    int prune();

    /**
     * 缓存是否已满,仅用于有空间限制的缓存对象
     *
     * @return 是否已满
     */
    boolean isFull();

    /**
     * 从缓存中移除对象
     *
     * @param key 键
     */
    void remove(K key);

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 缓存的对象数量
     * @return 缓存的对象数量
     */
    int size();

    /**
     * 缓存是否为空
     *
     * @return 缓存是否为空
     */
    boolean isEmpty();

    /**
     * 是否包含key
     *
     * @param key 键
     * @return 是否包含key
     */
    boolean containsKey(K key);

    /**
     * 设置监听
     *
     * @param listener 监听
     * @return this
     * @since 5.5.2
     */
    //todo 监听实现
//    default Cache<K, V> setListener(CacheListener<K, V> listener){
//        return this;
//    }















}
