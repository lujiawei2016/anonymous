package com.anonymous.redis.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * 
 * @description     redis工具类
 * @author          lujiawei
 * @data            2016年12月4日 下午2:41:05
 * @version         v1.0
 */
@Component("redisUtils")
public class RedisUtilsImpl implements RedisUtils {
	
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 将字符串放入到redis
	 */
	@Override
	public void put(String key, String value) {
		redisTemplate.multi();
		if (key==null || "".equals(key)) {  
            return;  
        }
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * 将字符串放入到redis并制定有效时间
	 */
	@Override
	public void put(String key, String value, long timeout, TimeUnit unit) {
		redisTemplate.multi();
		if (key==null || "".equals(key)) {  
            return;  
        }
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	/**
	 * 将对象放入到redis
	 */
	@Override
	public void put(String key, Object value) {
		redisTemplate.multi();
		if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(key, key, new Gson().toJson(value));
	}
	
	/**
	 * 将对象放入到redis并制定有效时间
	 */
	@Override
	public void put(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.multi();
		if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(key, key, new Gson().toJson(value));
        redisTemplate.expire(key, timeout, unit);
	}

	/**
	 * 通过key获取redis对象
	 */
	@Override
	public <T> T get(String key, Class<T> className) {
		Object obj = redisTemplate.opsForHash().get(key, key);  
        if(obj == null){  
            return null;  
        }  
        return new Gson().fromJson(""+obj, className);
	}

	/**
	 * 通过key获取redis字符串
	 */
	@Override
	public String get(String key) {
		Object obj = redisTemplate.opsForValue().get(key);
		 if(obj == null){
			 return null;
		 }else{
			 return String.valueOf(obj);
		 }
	}
	
	/**
	 * 提交事务
	 */
	@Override
	public void exec(){
		redisTemplate.exec();
	}

}
