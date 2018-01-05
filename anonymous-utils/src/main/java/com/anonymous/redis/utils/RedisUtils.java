package com.anonymous.redis.utils;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @description     redis工具类接口
 * @author          lujiawei
 * @data            2016年12月4日 下午2:09:25
 * @version         v1.0
 */
public interface RedisUtils {

	/**
	 * 
	 * @description     将字符串放入到redis中
	 * @author          lujiawei
	 * @data            2016年12月4日 下午2:09:37
	 * @version         v1.0
	 * @param key
	 * @param value
	 */
	public void put(String key, String value);
	
	/**
	 * 
	 * @Description  将字符串放入到redis中并指定有效时间
	 * @author       lujiawei
	 * @date         2016年12月9日 下午2:23:58
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	public void put(String key, String value,long timeout,TimeUnit unit);

	/**
	 * 
	 * @description     将对象放入到redis中
	 * @author          lujiawei
	 * @data            2016年12月4日 下午2:09:53
	 * @version         v1.0
	 * @param key
	 * @param value
	 */
    public void put(String key, Object value);
    
    /**
     * 
     * @Description  将对象放入到redis中并制定有效时间
     * @author       lujiawei
     * @date         2016年12月9日 下午2:25:11
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void put(String key, Object value,long timeout,TimeUnit unit);
  
    /**
     * 
     * @description     获取对象
     * @author          lujiawei
     * @data            2016年12月4日 下午2:10:16
     * @version         v1.0
     * @param key
     * @param className
     * @return
     */
    public <T> T get(String key, Class<T> className);
  
    /**
     * 
     * @description     获取对象
     * @author          lujiawei
     * @data            2016年12月4日 下午2:10:26
     * @version         v1.0
     * @param key
     * @return
     */
    public String get(String key);
    
    /**
     * 
     * @Description  提交事务
     * @author       lujiawei
     * @date         2016年12月5日 上午10:27:51
     */
	public void exec();
    
}