package com.anonymous.story.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.story.dao.StoryDao;
import com.anonymous.story.service.StoryService;

/**
 * 故事发布service实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月24日下午4:43:31
 */
@Service
@Transactional
public class StoryServiceImpl implements StoryService {
	
	private static final Logger logger = Logger.getLogger(StoryServiceImpl.class);
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private StoryDao storyDao;

	/**
	 * 故事发布
	 */
	@Override
	public Object release(String anonymId, String story_title, String story_article) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(story_title) && !StringUtils.isBlank(story_article)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(anonym != null){
				String storyId = UUID.randomUUID().toString();
				Date createTime = new Date();
				Map<String, Object> map = new HashMap<>();
				map.put("storyId", storyId);
				map.put("storyTitle", story_title);
				map.put("storyContent", story_article);
				map.put("updateTime", createTime);
				map.put("createTime", createTime);
				map.put("anonymId", anonymId);
				
				storyDao.saveStory(map);
				
				logger.info(anonymId+"发布故事成功");
				
				result = "1";
				msg = "发布故事成功";
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
