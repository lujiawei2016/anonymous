package com.anonymous.story.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonymous.dao.AnonymousDao;
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
