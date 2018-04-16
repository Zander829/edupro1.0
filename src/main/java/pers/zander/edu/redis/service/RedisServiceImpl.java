package pers.zander.edu.redis.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 *
 * @author zhaozhaob
 * @version 1.0.0
 * 2018年4月16日 下午8:35:37
 */
@Service
public class RedisServiceImpl implements IRedisService {

	private static Logger logger = Logger.getLogger(RedisServiceImpl.class);

	@Autowired
	private JedisPool jedisPool;

	@Override
	public Jedis getResource() {
		return jedisPool.getResource();
	}

	@Override
	public void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResourceObject(jedis);
		}
	}

	@Override
	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
			logger.info("Redis set success - " + key + ", value:" + value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + value);
		} finally {
			returnResource(jedis);
		}
	}

	@Override
	public String get(String key) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.get(key);
			logger.info("Redis get success - " + key + ", value:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + result);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

}
