package com.class8.eduAdmin.redis;

import redis.clients.jedis.Jedis;

public interface RedisDataSource {
	
	/**
	 * 获取Redis的客户端，可以执行命令
	 * @return
	 */
	public abstract Jedis getRedisClient();
	
	/**
	 * 将资源返回给pool
	 * @param shardedJedis
	 */
	public void returnResource(Jedis jedis);
	
	/**
	 * 出现异常后，将资源返回给pool
	 * @param shardedJedis
	 * @param broken
	 */
	public void returnResource(Jedis jedis,boolean broken);
	
}
