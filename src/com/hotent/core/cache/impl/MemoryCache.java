package com.hotent.core.cache.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.hotent.core.cache.ICache;
import com.hotent.core.util.MapUtil;

/**
 * 内存缓存实现，缓存放到本地的一个hashMap中。
 * <pre>
 * 方法说明参考：
 * {@link com.hotent.core.cache.ICache 缓存接口}
 * </pre>
 */
public class MemoryCache implements ICache {
	
	private Map<String, Object > cache=(Map<String, Object>) Collections.synchronizedMap(new  HashMap<String, Object >());

	@Override
	public synchronized  void add(String key, Object obj, int timeout) {
		cache.put(key, obj);
		
	}
	
	@Override
	public synchronized  void add(String key, Object obj) {
		cache.put(key, obj);
	}


	@Override
	public synchronized  void delByKey(String key) {
		cache.remove(key);
		
	}

	@Override
	public  void clearAll() {
		cache.clear();
		
	}

	@Override
	public synchronized  Object getByKey(String key) {
		return cache.get(key);
	}

	@Override
	public boolean containKey(String key) {
		return cache.containsKey(key);
	}

	
}
