package com.anonymous.story.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.anonymous.story.pojo.Story;
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
	 * 查找最新故事
	 */
	@Override
	public Object searchNewStory() throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		
		List<Map<String, Object>> storyList = storyDao.searchNewStory();
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		resultMap.put("storyList", storyList);
		return resultMap;
	}

	/**
	 * 故事发布
	 */
	@Override
	public Object release(String anonymId, String story_title, String story_article, String story_article_summary) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(story_title) && !StringUtils.isBlank(story_article) && !StringUtils.isBlank(story_article_summary)){
			
			//摘要概述
			if(story_article_summary.contains("<br/>") || story_article_summary.contains("<br>")){
				story_article_summary = story_article_summary.replaceAll("<br/>", "");
				story_article_summary = story_article_summary.replaceAll("<br>", "");
			}
			if(story_article_summary.length() >= 60){
				story_article_summary = story_article_summary.substring(0, 59);
			}
			
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
				map.put("storyArticleSummary", story_article_summary);
				
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

	/**
	 * 根据id查找故事
	 */
	@Override
	public Object findStoryById(String storyId,String anonymId) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(storyId)){
			Story story = storyDao.getStoryDetailInfo(storyId, anonymId);
			if(story != null){
				result = "1";
				msg = "获取成功";
				resultMap.put("storyContent", story.getStoryContent());
				resultMap.put("authorId", story.getAnonym().getAnonymId());
				
				//获取是否点赞、是否收藏、评论数量
				if(!StringUtils.isBlank(anonymId)){
					Anonym anonym = anonymousDao.findAnonymById(anonymId);
					if(anonym != null){
						Integer collectionNum = storyDao.getCollectionByAnonymId(storyId, anonymId);
						Integer fabulousNum = storyDao.getFabulousByAnonymId(storyId, anonymId);
						
						resultMap.put("collectionNum", collectionNum);   // 故事是否收藏
						resultMap.put("fabulousNum", fabulousNum);       // 故事是否点赞
						
					}
				}
				Integer commentNum = storyDao.getStoryCommentNum(storyId);
				resultMap.put("commentNum", commentNum);                  // 评论数量
			}else{
				result = "3";
				msg = "获取失败";
			}
		}else{
			result = "2";
			msg = "故事不合法";
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

	/**
	 * 分页查询故事
	 */
	@Override
	public Object pagingSearchStory(String anonymId, String offset, String length) throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		if(StringUtils.isNumeric(offset) && StringUtils.isNumeric(length)){
			List<Map<String, Object>> storyList = storyDao.pagingSearchStory(anonymId, Integer.parseInt(offset), Integer.parseInt(length));
			resultMap.put("storyList", storyList);
			result = "1";
			msg = "查询成功";
		}else{
			result = "2";
			msg = "参数不合符";
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}
}
