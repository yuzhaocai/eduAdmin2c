package com.class8.eduAdmin.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.class8.eduAdmin.memcached.SpyMemcachedClient;

public class MemcachedCacheManager extends AbstractCacheManager {
	
	private SpyMemcachedClient client;

	@SuppressWarnings({"unchecked", "rawtypes" })
	@Override
	protected Cache<String,Object> createCache(String name) throws CacheException {
		return new MemcachedCache(name,client);
	}

	public SpyMemcachedClient getClient() {
		return client;
	}

	public void setClient(SpyMemcachedClient client) {
		this.client = client;
	}
	
}
