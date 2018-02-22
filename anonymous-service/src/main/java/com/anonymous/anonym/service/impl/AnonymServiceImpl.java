package com.anonymous.anonym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonym.service.AnonymService;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.redis.utils.RedisUtils;

/**
 * 用户service实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月22日上午10:49:24
 */
public class AnonymServiceImpl implements AnonymService {
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Value("${judgeAnonymKey}")
	private String judgeAnonymKey;
	
	@Autowired
	private RedisUtils redisUtils;

	/**
	 * 判断用户是否存在
	 */
	@Override
	public boolean judgeAnonym(String anonymId, String password) {
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(password)){
			//判断redis中是否含有该键
			String redisKey = judgeAnonymKey+"_"+anonymId;  // redis中的键
			String redis_password = redisUtils.get(redisKey);
			if(!StringUtils.isBlank(redis_password)){
				if(redis_password.equals(password)){
					//密码和redis的相同
					return true;
				}else{
					//密码和redis不相同
					return false;
				}
			}else{
				//redis中没有该键
				Anonym anonym = anonymousDao.findAnonymById(anonymId);
				if(anonym != null){
					//当密码等于快捷登陆密码或者登陆密码
					if(password.equals(anonym.getPassword()) || password.equals(anonym.getQuickPassword())){
						//将id和密码放入到redis中
						redisUtils.put(redisKey, password);
						redisUtils.exec();
						
						return true;
					}
				}
			}
		}
		return false;
	}


}
