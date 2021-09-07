package com.akk.cache.file;

import com.akk.cache.Cache;

import java.io.File;
import java.io.Serializable;

/**
 * 文件缓存,解决频繁读取文件引起的性能问题
 */
public abstract class AbstractFileCache implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 容量
     */
    protected final int capacity;
    /**
     * 缓存文件的最大文件大小
     */
    protected final int maxFileSize;
    /**
     * 默认超时时间
     */
    protected final long timeout;
    /**
     * 缓存的实现
     */
    protected final Cache<File,byte[]> cache;
    /**
     * 已使用缓存空间
     */
    protected  int usedSize;

    public int getCapacity() {
        return capacity;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public long getTimeout() {
        return timeout;
    }

    public Cache<File, byte[]> getCache() {
        return cache;
    }

    public int getUsedSize() {
        return usedSize;
    }

    public int getCachedFilesCount(){
        return cache.size();
    }

    public void clear(){
        cache.clear();
        usedSize = 0;
    }

    public byte[] getFileBytes(String path){

    }

    public byte[] getFileBytes(File file){

    }

    public AbstractFileCache(int capacity, int maxFileSize, long timeout) {
        this.capacity = capacity;
        this.maxFileSize = maxFileSize;
        this.timeout = timeout;
        this.cache = initCache();
    }

    protected abstract Cache<File,byte[]> initCache();
}
