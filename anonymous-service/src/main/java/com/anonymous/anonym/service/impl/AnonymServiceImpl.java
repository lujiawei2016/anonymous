package com.anonymous.anonym.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonym.service.AnonymService;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.card.dao.CardDao;
import com.anonymous.card.pojo.Card;
import com.anonymous.redis.utils.RedisUtils;
import com.anonymous.story.dao.StoryDao;
import com.anonymous.story.pojo.Story;

/**
 * 用户service实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月22日上午10:49:24
 */
public class AnonymServiceImpl implements AnonymService {
	
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
				//获取该用户发布的卡片
				List<Map<String, Object>> cardList = cardDao.findCardByAnonymId(anonymId);
				
				//获取该用户发布的故事
				List<Map<String, Object>> storyList = storyDao.findStoryByAnonymId(anonymId);
				
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
				
				//封装卡片信息
				
				resultMap.put("anonymMap", anonymMap);//用户
				//resultMap.put("storyList", storyList);//用户
				//resultMap.put("cardList", cardList);//用户
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}
}
