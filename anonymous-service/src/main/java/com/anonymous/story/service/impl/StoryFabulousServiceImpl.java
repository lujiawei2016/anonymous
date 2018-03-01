package com.anonymous.story.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.mq.utils.ActiveMQUtils;
import com.anonymous.redis.utils.RedisUtils;
import com.anonymous.story.dao.StoryDao;
import com.anonymous.story.pojo.Story;
import com.anonymous.story.service.StoryFabulousService;

/**
 * 故事点赞实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月1日下午4:49:37
 */
@Service
@Transactional
public class StoryFabulousServiceImpl implements StoryFabulousService {
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private StoryDao storyDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${story_fabulous_num}")
	private String story_fabulous_num;
	
	@Autowired
	private ActiveMQUtils activeMQUtils;

	/**
	 * 故事点赞
	 */
	@Override
	public Object fabulous(String anonymId, String storyId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(storyId)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			Story story = storyDao.findStoryById(storyId);
			if(anonym != null && story != null){
				String redis_story_key = anonymId+"_"+storyId;
				String isFabulous = redisUtils.get(redis_story_key);
				if(StringUtils.isBlank(isFabulous)){
					//没有点赞
					
					//在redis中进行运算，再放到activemq中进行数据库的增减
					String redis_fabulous_key = story_fabulous_num+"_"+storyId;
					String storyFabulousNum = redisUtils.get(redis_fabulous_key);
					int fabulousNum = 1;
					if(!StringUtils.isBlank(storyFabulousNum)){
						fabulousNum = Integer.parseInt(storyFabulousNum);
						fabulousNum = fabulousNum + 1;
					}
					
					//将点赞+1后再写入到redis中
					redisUtils.put(redis_fabulous_key, fabulousNum+"");
					
					//将最新的数据放到activemq中进行数据库的插入
					HashMap<String, Object> mqMap = new HashMap<>();
					mqMap.put("storyFabulousId", UUID.randomUUID().toString());
					mqMap.put("storyId", storyId);
					mqMap.put("anonymId", anonymId);
					mqMap.put("createTime", new Date());
					mqMap.put("updateTime", new Date());
					activeMQUtils.sendMessage("storyFabulous", mqMap);
					
					//点赞成功进行redis标记
					redisUtils.put(redis_fabulous_key, "1");
					redisUtils.exec();
					
					resultMap.put("fabulousNum", fabulousNum);
					result = "1";
					msg = "点赞成功";
				}
			}else{
				result = "2";
				msg = "系统繁忙，请稍后重试";
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
