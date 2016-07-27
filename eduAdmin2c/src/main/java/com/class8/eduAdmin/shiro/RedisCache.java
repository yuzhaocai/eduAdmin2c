package com.class8.eduAdmin.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

import com.class8.eduAdmin.redis.RedisClientTemplate;
import com.class8.eduAdmin.util.SerializeUtil;
/**
 * redis中获取所有key的命令
 * 	*keys *:支持通配符的形式获取key，一般通过命名空间或前缀的形式来获取key
 *	*info:可以看到所有库的key的数量
 *  *dbsize:获取当前库中key的数量，统计库中所有的key
 *  *keys *:统计的是当前库中有效的key,获得是库中有效的key，只能用在数据量小，大的时候能搞死生产环境
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("unchecked")
public class RedisCache<K,V> implements Cache<K,V> {
	
	private RedisClientTemplate cache;
	
	private String keyPrefix;
	
	public RedisCache(String name,RedisClientTemplate cache){
		if(StringUtils.isEmpty(name)){
			throw new IllegalArgumentException("Cache name cannot be null or empty.");
		}
		if(cache == null){
			throw new IllegalArgumentException("Cache client instance cannot be null.");
		}
		this.keyPrefix = name + ":";
		this.cache = cache;
	}

	public void clear() throws CacheException {
		Set<byte[]> kbytes = cache.keys(SerializeUtil.serialize(keyPrefix + "*"));
		if(kbytes != null){
			for (byte[] kbyte : kbytes) {
				cache.del(new String(kbyte));
			}
		}
	}

	public V get(K key) throws CacheException {
		byte[] kbyte = SerializeUtil.serialize(keyToString(key));
		byte[] vbyte = cache.get(kbyte);
		return (V) SerializeUtil.unserialize(vbyte);
	}

	public Set<K> keys() {
		Set<byte[]> kbytes = cache.keys(SerializeUtil.serialize(keyPrefix + "*"));
		if(kbytes != null){
			Set<K> keys = new HashSet<K>();
			for (byte[] kbyte : kbytes) {
				keys.add((K) SerializeUtil.unserialize(kbyte));
			}
			return keys;
		} else {
			return new HashSet<K>();
		}
	}

	public V put(K key, V value) throws CacheException {
		V previos = get(key);
		byte[] kbyte = SerializeUtil.serialize(keyToString(key));
		byte[] vbyte = SerializeUtil.serialize(value);
		cache.set(kbyte, vbyte);
		return previos;
	}

	public V remove(K key) throws CacheException {
		V previos = get(key);
		cache.del(keyToString(key));
		return previos;
	}

	public int size() {
		Set<byte[]> kbytes = cache.keys(SerializeUtil.serialize(keyPrefix + "*"));
		if(kbytes != null){
			return kbytes.size();
		}
		return 0;
	}

	public Collection<V> values() {
		Set<byte[]> kbytes = cache.keys(SerializeUtil.serialize(keyPrefix + "*"));
		if(kbytes != null){
			List<V> values = new LinkedList<V>();
			for (byte[] kbyte : kbytes) {
				values.add((V) SerializeUtil.unserialize(cache.get(kbyte)));
			}
			return values;
		} else {
			return new LinkedList<V>();
		}
	}
	
	private String keyToString(K key){
		String k = String.valueOf(key);
		if(StringUtils.startsWith(k, keyPrefix)){
			return k;
		}
		return keyPrefix + k;
	}

}
