package com.class8.eduAdmin.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/*
 * 目前先不启用redis，如果需要启用，请打开Component注解
 */
//@Component("redisDataSource")
public class RedisDataSourceImple implements RedisDataSource {
	
	private static final Logger log = Logger.getLogger(RedisDataSourceImple.class);
	
	@Autowired
	private JedisPool jedisPool;

	public Jedis getRedisClient() {
		try {
			return jedisPool.getResource();
		} catch (Exception e) {
			log.error("getRedisClient error.",e);
		}
		return null;
	}

	public void returnResource(Jedis jedis) {
		jedisPool.returnResource(jedis);
		
	}

	public void returnResource(Jedis jedis, boolean broken) {
		if(broken){
			jedisPool.returnBrokenResource(jedis);
		} else {
			jedisPool.returnResource(jedis);
		}
	}

}
