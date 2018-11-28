package redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisUtil {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public Object get(String key) {
		Object o = new Object();
		if (!hasKey(key)) {
			return null;
		}

		o = redisTemplate.opsForValue().get(key);
		return o;
	}

	public void delete(String key) {
		redisTemplate.delete(key);
	}

	// hash
	public Object getHash(String hashKey) {
		return redisTemplate.opsForHash().entries(hashKey);
	}

	public void setHashValue(String hashKey, String key, Object value) {
		redisTemplate.opsForHash().put(hashKey, key, value);
	}

	public void setHashValue(String hashKey, Map<String, Object> map) {
		redisTemplate.opsForHash().putAll(hashKey, map);
	}

	public void removeHashValue(String hashKey, String key) {
		redisTemplate.opsForHash().delete(hashKey, key);
	}

	public void removeHashValue(String hashKey) {
		redisTemplate.delete(hashKey);
	}

	/**
	 * 设置redis 固定时间自动消除
	 * 
	 * @param key
	 * @param time
	 */
	public void expire(String key, long time) {
		redisTemplate.expire(key, time, TimeUnit.SECONDS);// 以秒为单位
	}

	// set
	public boolean cacheSet(String key, Object value, long time) {
		try {
			SetOperations<String, Object> valueOps = redisTemplate.opsForSet();
			valueOps.add(key, value);
			if (time > 0)
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Throwable t) {
		}
		return false;
	}

	public boolean cacheSet(String k, String v) {
		return cacheSet(k, v, -1);
	}

	public void removeSet(String key, String value) {
		redisTemplate.opsForSet().remove(key, value);
	}

	public Set<Object> getSet(String key) {
		try {
			SetOperations<String, Object> setOps = redisTemplate.opsForSet();
			return setOps.members(key);
		} catch (Throwable t) {
		}
		return null;
	}

	public Boolean setIsMember(String key, String value) {
		try {
			SetOperations<String, Object> setOps = redisTemplate.opsForSet();
			return setOps.isMember(key, value);
		} catch (Throwable t) {
		}
		return null;
	}

	// list
	public boolean cacheList(String key, Object v, long time) {
		try {
			ListOperations<String, Object> listOps = redisTemplate.opsForList();
			listOps.rightPush(key, v);
			if (time > 0)
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Throwable t) {
		}
		return false;
	}

	public boolean cacheList(String k, Object v) {
		return cacheList(k, v, -1);
	}

	public boolean cacheList(String key, List<Object> v, long time) {
		try {
			ListOperations<String, Object> listOps = redisTemplate.opsForList();
			long l = listOps.rightPushAll(key, v);
			if (time > 0)
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			return true;
		} catch (Throwable t) {
		}
		return false;
	}

	public boolean cacheList(String k, List<Object> v) {
		return cacheList(k, v, -1);
	}

	public List<Object> getList(String key, long start, long end) {
		try {
			ListOperations<String, Object> listOps = redisTemplate.opsForList();
			return listOps.range(key, start, end);
		} catch (Throwable t) {
		}
		return null;
	}

	public List<Object> getList(String key) {
		try {
			ListOperations<String, Object> listOps = redisTemplate.opsForList();
			List<Object> o = listOps.range(key, 0, -1);
			if (o != null) {
				return (List<Object>) o;
			}
		} catch (Throwable t) {
		}
		return null;
	}

	public long getListSize(String key) {
		try {
			ListOperations<String, Object> listOps = redisTemplate.opsForList();
			return listOps.size(key);
		} catch (Throwable t) {
		}
		return 0;
	}

	public boolean removeOneOfList(String key) {
		try {
			ListOperations<String, Object> listOps = redisTemplate.opsForList();
			listOps.rightPop(key);
			return true;
		} catch (Throwable t) {
		}
		return false;
	}

	// zset
	public void cacheZSet(String key, double score, Object member) {
		redisTemplate.opsForZSet().add(key, member, score);
	}

	public Set<Object> getZSet(String key, double start, double stop) {
		return redisTemplate.opsForZSet().rangeByScore(key, start, stop);
	}

	public Set<Object> getZSet(String key) {
		return redisTemplate.opsForZSet().range(key, 0, -1);
	}

	public void removeZSet(String key, Object member) {
		redisTemplate.opsForZSet().remove(key, member);
	}

	public Long getZSetSize(String key) {
		return redisTemplate.opsForZSet().size(key);
	}

	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}
}
