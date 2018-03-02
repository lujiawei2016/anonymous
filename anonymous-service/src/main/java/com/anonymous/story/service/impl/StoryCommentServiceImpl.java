package com.anonymous.story.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.anonymous.anonym.pojo.Anonym;
import com.anonymous.anonymous.dao.AnonymousDao;
import com.anonymous.story.dao.StoryCommentDao;
import com.anonymous.story.dao.StoryDao;
import com.anonymous.story.pojo.Story;
import com.anonymous.story.service.StoryCommentService;

@Service
@Transactional
public class StoryCommentServiceImpl implements StoryCommentService {
	
	@Autowired
	private AnonymousDao anonymousDao;
	
	@Autowired
	private StoryDao storyDao;
	
	@Autowired
	private StoryCommentDao storyCommentDao;

	/**
	 * 评论故事
	 */
	@Override
	public Object comment(String anonymId, String storyReplyCommentId, String storyId, String commentContent)
			throws Exception {
		String result = "0";
		String msg = "系统繁忙，请稍后重试";
		Map<String, Object> resultMap = new HashMap<>();
		
		if(!StringUtils.isBlank(anonymId) && !StringUtils.isBlank(storyId) && !StringUtils.isBlank(commentContent)){
			Anonym anonym = anonymousDao.findAnonymById(anonymId);
			Story story = storyDao.findStoryById(storyId);
			if(anonym != null && story != null){
				String storyCommentId = UUID.randomUUID().toString();
				Map<String, Object> map = new HashMap<>();
				map.put("storyCommentId", storyCommentId);
				map.put("storyCommentContent", commentContent);
				
				//判断回复ID是否合法
				if(!StringUtils.isBlank(storyReplyCommentId)){
					Anonym replyAnonym = anonymousDao.findAnonymById(storyReplyCommentId);
					if(replyAnonym != null){
						map.put("styroReplyCommentId", storyReplyCommentId);
					}else{
						map.put("styroReplyCommentId", null);
					}
				}else{
					map.put("styroReplyCommentId", null);
				}
				
				map.put("createTime", new Date());
				map.put("updateTime", new Date());
				map.put("storyId", storyId);
				map.put("anonymId", anonymId);
				
				storyCommentDao.comment(map);
				
				result = "1";
				msg = "评论成功";
			}
		}
		
		resultMap.put("result", result);
		resultMap.put("msg", msg);
		
		return resultMap;
	}

}
