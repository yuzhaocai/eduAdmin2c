package com.class8.eduAdmin.shiro;

import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.Subject;

import com.class8.eduAdmin.memcached.SpyMemcachedClient;

/**
 * 基于Memcached实现的Cache
 *	memcached因为性能的缘故， 没有提供遍历整个缓存当中对象的功能，不过memcached也提供了很多命令来监控memcached的状态，
 */
public class MemcachedCache<K,V> implements Cache<K, V>{
	
	private SpyMemcachedClient cache;
	private String keyPerfix;  
	/**
	 * 注意：Memcached的过期时间和Session的过期时间不同 ，Session的过期时间是指在设定的时间内用户没有访问服务器端，session就会失效
	 * 		 而memcached的过期时间是指值在缓存中存活的时间，不管中间有没有访问，该值都只存储这么长时间。
	 */
	private static final int DEFAULT_EXPIRED_TIME = 60*60*12;//默认存储的时间为12小时
	
	private static final Logger log = Logger.getLogger(MemcachedCache.class);
	
	public MemcachedCache(String name,SpyMemcachedClient cache){
		if(StringUtils.isEmpty(name)){
			throw new IllegalArgumentException("Cache name cannot be null or empty.");
		}
		if(cache == null){
			throw new IllegalArgumentException("Memcached client instance cannot be null or empty.");
		}
		this.keyPerfix = name + ":";
		this.cache = cache;
	}
	/**
	 * memcached的flush_all命令会导致memcached缓存中没有失效的数据全部失效,而我们想要的是只是清除指定“keyPerfiex”的key的数据失效，我们可以通过前缀来模仿命名空间，
	 * 即是在key中添加版本号的思路，并将该key的版本号添加到memcached，以后每次获取数据的时候，先从memcached中获取key，然后动态拼接key，memcached本身不支持命名空间删除数据的删除，可以通过升级版本号，来实现批量删除的效果，
	 * 但这时候数据并没有真正从memcached中删除，而是等到数据过期才删除。
	 */
	public void clear() throws CacheException {
		//TODO
	}

	public V get(K key) throws CacheException {
		return cache.get(keyToString(key));
	}
	
	/**
	 * TODO 使用Memcached作为缓存的弊端，没有办法获取所有的keys
	 * 弊端：只能通过stats items来间接的获取所有的key,并且获取的values也是整个memcached中个的values，也不是某个cacheName所指定的values
	 */
	public Set<K> keys() {
		Set keys = new HashSet();
        Map dumps = new HashMap();
        Map slabs = cache.getMemcachedClient().getStats("stats items");
        if(slabs != null && slabs.keySet() != null)
        {
            for(Iterator itemsItr = slabs.keySet().iterator(); itemsItr.hasNext();)
            {
                String server = ((String)itemsItr.next()).toString();
                Map itemNames = (Map)slabs.get(server);
                for(Iterator itemNameItr = itemNames.keySet().iterator(); itemNameItr.hasNext();)
                {
                    String itemName = ((String)itemNameItr.next()).toString();
                    String itemAtt[] = itemName.split(":");
                    if(itemAtt[2].startsWith("number"))
                        dumps.put(itemAtt[1], Integer.valueOf(Integer.parseInt(itemAtt[1])));
                }

            }

            if(!dumps.values().isEmpty())
            {
                for(Iterator dumpIter = dumps.values().iterator(); dumpIter.hasNext();)
                {
                    int dump = ((Integer)dumpIter.next()).intValue();
                    Map cacheDump = cache.getMemcachedClient().getStats("stats cachedump " + dump +" 0");
                    for(Iterator entryIter = cacheDump.values().iterator(); entryIter.hasNext();)
                    {
                        Map items = (Map)entryIter.next();
                        for(Iterator ks = items.keySet().iterator(); ks.hasNext();)
                        {
                            String k = (String)ks.next();
                            try
                            {
                                k = URLDecoder.decode(k, "UTF-8");
                            }
                            catch(Exception ex)
                            {
                                ex.printStackTrace();
                            }
                            if(k != null && !k.trim().equals(""))
                            	keys.add(k);
                                /*
                                if(fast)
                                    keys.add(k);
                                else
                                if(containsKey(k))
                                    keys.add(k);
                                */
                        }

                    }

                }

            }
        }
        return keys;
	}
	
	public V put(K key, V value) throws CacheException {
		cache.getMemcachedClient().set(keyToString(key), DEFAULT_EXPIRED_TIME, value);
		return value;
	}

	public V remove(K key) throws CacheException {
		V value = cache.get(keyToString(key));
		cache.delete(keyToString(key));
		return value;
	}
	
	public int size() {
		return this.keys().size();
	}
	
	/**
	 *
	 * TODO 使用Memcached作为缓存的弊端，没有办法获取所有的keys
	 * 弊端：只能通过stats items来间接的获取所有的key,并且获取的values也是整个memcached中个的values，也不是某个cacheName所指定的values
	 */
	@SuppressWarnings("rawtypes")
	public Collection<V> values() {

        Set values = new HashSet();
        Map dumps = new HashMap();
        //以命名的方式来执行
        Map slabs = cache.getMemcachedClient().getStats("stats items");
        if(slabs != null)
        {
            for(Iterator iterator = slabs.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Map temp = (Map)entry.getValue();
                if(temp != null)
                {
                    for(Iterator iterator2 = temp.entrySet().iterator(); iterator2.hasNext();)
                    {
                        java.util.Map.Entry items = (java.util.Map.Entry)iterator2.next();
                        String itemName = (String)items.getKey();
                        String itemAtt[] = itemName.split(":");
                        if(itemAtt[2].startsWith("number"))
                            dumps.put(itemAtt[1], Integer.valueOf(Integer.parseInt(itemAtt[1])));
                    }

                }
            }

            for(Iterator iterator1 = dumps.entrySet().iterator(); iterator1.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                int dump = ((Integer)entry.getValue()).intValue();
                Map cacheDump = cache.getMemcachedClient().getStats("stats cachedump " + dump +" 50000");
                for(Iterator iterator3 = cacheDump.entrySet().iterator(); iterator3.hasNext();)
                {
                    java.util.Map.Entry temp = (java.util.Map.Entry)iterator3.next();
                    Map items = (Map)temp.getValue();
                    for(Iterator iterator4 = items.entrySet().iterator(); iterator4.hasNext();)
                    {
                        java.util.Map.Entry item = (java.util.Map.Entry)iterator4.next();
                        K k = (K) item.getKey();
                        if(k != null)
                        {
                            Object value = get(k);
                            if(value != null)
                                values.add(value);
                        }
                    }

                }

            }

        }
        return values;
	}
	
	private String keyToString(K key){
		String k = String.valueOf(key);
		if(StringUtils.startsWith(k, this.keyPerfix)){
			return k;
		}
		return this.keyPerfix + k;
	}

}
