package com.class8.eduAdmin.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

import com.class8.eduAdmin.redis.RedisClientTemplate;

public class RedisCacheManager extends AbstractCacheManager {
	
	private RedisClientTemplate client;
	
	@Override
	protected Cache createCache(String name) throws CacheException {
		return new RedisCache(name,client);
	}

	public RedisClientTemplate getClient() {
		return client;
	}

	public void setClient(RedisClientTemplate client) {
		this.client = client;
	}

}
