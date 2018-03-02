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
import com.anonymous.story.dao.StoryCollectionDao;
import com.anonymous.story.dao.StoryDao;
import com.anonymous.story.pojo.Story;
import com.anonymous.story.service.StoryCollectionService;

/**
 * 故事收藏实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2018年3月2日上午9:59:45
 */
@Service
@Transactional
public class StoryCollectionServiceImpl implements StoryCollectionService {
	
	private static final Logger logger = Logger.getLogger(StoryCollectionServiceImpl.class);
	
	@Autowired
	private StoryDao storyDao;
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private StoryCollectionDao storyCollectionDao;

	/**
	 * 故事收藏
	 */
	@Override
	public Object collection(String storyId, String anonymId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		if(!StringUtils.isBlank(storyId) && !StringUtils.isBlank(anonymId)){
			Story story = storyDao.findStoryById(storyId);
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			if(story != null && anonym != null){
				Integer collectionNum = storyCollectionDao.isCollection(storyId, anonymId);
				if(collectionNum == null || collectionNum == 0){
					//没有收藏，进行收藏
					String storyCollectiionId = UUID.randomUUID().toString();
					Map<String, Object> map = new HashMap<>();
					map.put("storyCollectiionId", storyCollectiionId);
					map.put("createTime", new Date());
					map.put("updateTime", new Date());
					map.put("anonymId", anonymId);
					map.put("storyId", storyId);
					storyCollectionDao.collection(map);
					
					result = "1";
					msg = "收藏成功";
					
					logger.info(anonymId+"收藏故事"+storyId+"成功");
				}
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
