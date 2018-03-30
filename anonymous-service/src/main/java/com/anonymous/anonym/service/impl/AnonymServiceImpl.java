package com.anonymous.anonym.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonym.service.AnonymService;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.card.dao.CardDao;
import com.anonymous.redis.utils.RedisUtils;
import com.anonymous.story.dao.StoryDao;

/**
 * 用户service实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月22日上午10:49:24
 */
public class AnonymServiceImpl implements AnonymService {
	
	private static final Logger logger = Logger.getLogger(AnonymServiceImpl.class);
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private StoryDao storyDao;
	
	@Autowired
	private CardDao cardDao;
	
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
				}
			}
			
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
		return false;
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public Object getAnonymInfoById(String anonymId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		if(!StringUtils.isBlank(anonymId)){
			Anonym anonym = anonymousDao.findAnonymInfoById(anonymId);
			if(anonym != null){
				
				result = "1";
				msg = "获取成功";
				
				//封装用户信息
				Map<String, Object> anonymMap = new HashMap<>();
				anonymMap.put("anonymId", anonym.getAnonymId());
				anonymMap.put("backgroundImg", anonym.getBackgroundImg() == null ? "" : anonym.getBackgroundImg());
				anonymMap.put("city", anonym.getCity() == null ? "" : anonym.getCity());
				anonymMap.put("deviceId", anonym.getDeviceId() == null ? "" : anonym.getDeviceId());
				anonymMap.put("headerImg", anonym.getHeaderImg() == null ? "" : anonym.getHeaderImg());
				anonymMap.put("nickName", anonym.getNickName() == null ? "" : anonym.getNickName());
				anonymMap.put("personalSignature", anonym.getPersonalSignature() == null ? "" : anonym.getPersonalSignature());
				anonymMap.put("phone", anonym.getPhone() == null ? "" : anonym.getPhone());
				anonymMap.put("sex", anonym.getSex() == null ? "" : anonym.getSex());
				anonymMap.put("userName", anonym.getUserName() == null ? "" : anonym.getUserName());
				
				resultMap.put("anonymMap", anonymMap);//用户
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

	/**
	 * 保存用户头像
	 */
	@Override
	public Object saveHeadImg(String headerImg, String anonymId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		if(!StringUtils.isBlank(headerImg) && !StringUtils.isBlank(anonymId)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(anonym != null){
				result = "1";
				msg = "更新成功";
				resultMap.put("headerImg", headerImg);
				anonym.setHeaderImg(headerImg);
				anonym.setUpdateTime(new Date());
				anonymousDao.updateQuickAnonym(anonym);
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}
	
	/**
	 * 保存背景图片
	 */
	@Override
	public Object saveBackgroundImg(String backgroundImg, String anonymId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		if(!StringUtils.isBlank(backgroundImg) && !StringUtils.isBlank(anonymId)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(anonym != null){
				result = "1";
				msg = "更新成功";
				resultMap.put("backgroundImg", backgroundImg);
				anonym.setBackgroundImg(backgroundImg);
				anonym.setUpdateTime(new Date());
				anonymousDao.updateQuickAnonym(anonym);
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

	/**
	 * 更新个人信息
	 */
	@Override
	public Object updateMe(String anonymId, String nickName, String personalSignature, String sex) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(anonym != null){
				if("1".equals(sex) || "2".equals(sex) || "3".equals(sex)){
					//sex必须为1或者2或者3,1-男；2-女；3-外星人
					anonym.setNickName(nickName);
					anonym.setPersonalSignature(personalSignature);
					anonym.setSex(sex);
					anonymousDao.updateQuickAnonym(anonym);
					
					result = "1";
					msg = "更新成功";
					
					logger.info("更新信息成功");
				}else{
					result = "2";
					msg = "性别不正确";
				}
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
