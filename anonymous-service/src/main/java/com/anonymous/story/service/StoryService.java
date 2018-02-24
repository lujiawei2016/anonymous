package com.anonymous.story.service;

/**
 * 故事service
 * @author  lujiawei
 * @version V1.0
 * @date    2018年2月24日下午4:41:39
 */
public interface StoryService {

	/**
	 * 发布故事
	 * @param anonymId       发布作者
	 * @param story_title    标题
	 * @param story_article  内容
	 * @return
	 * @throws Exception
	 */
	public Object release(String anonymId,String story_title,String story_article) throws Exception;
}
